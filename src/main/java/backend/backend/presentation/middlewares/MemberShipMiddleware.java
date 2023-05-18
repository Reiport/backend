package backend.backend.presentation.middlewares;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import backend.backend.application.common.interfaces.IAuthorizationFacade;
import backend.backend.application.services.RequestService;
import backend.backend.domain.entities.Request;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MemberShipMiddleware extends OncePerRequestFilter {

    @Autowired
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    private RequestService requestService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/requests/client") || servletPath.startsWith("/requests/handle")) {
            return true;
        }

        if (request.getParameter("id") == null) {
            return true;
        }

        return !servletPath.startsWith("/requests");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestId = request.getParameter("id");

        Request workingRequest = requestService.getRequest(Integer.parseInt(requestId));

        if (!requestService.checkMemberShip(workingRequest, authorizationFacade.getAuthenticatedUser())) {
            return;
        }

        doFilter(request, response, filterChain);

    }

}
