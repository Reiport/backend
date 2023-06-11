package backend.backend.presentation.middlewares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.presentation.errors.common.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationMiddleware extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final IJwtGenerator jwtGenerator;

    public AuthorizationMiddleware(UserDetailsService userDetailsService, IJwtGenerator jwtGenerator) {
        this.userDetailsService = userDetailsService;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String userEmail;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);

        DecodedJWT decodedToken;

        try {

            decodedToken = jwtGenerator.decodeToken(jwtToken);

        } catch (Exception e) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.FORBIDDEN,
                    "Não está autorizado a realizar esta operação",
                    request.getRequestURL().toString());

            response.setStatus(errorResponse.getCode());
            String json = objectMapper.writeValueAsString(errorResponse);

            // Set the response content type and write the JSON message
            response.setContentType("application/json");
            response.getWriter().write(json);
            response.getWriter().flush();
            return;
        }

        userEmail = decodedToken
                .getClaim("email")
                .asString();

        var userRole = decodedToken
                .getClaim("role")
                .asString();

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jwtGenerator.validateToken(jwtToken)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null,
                        new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(userRole))));

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);

    }

}
