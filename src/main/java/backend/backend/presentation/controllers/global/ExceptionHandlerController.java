package backend.backend.presentation.controllers.global;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;

import backend.backend.presentation.errors.authentication.BaseException;
import backend.backend.presentation.errors.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BaseException e, HttpServletRequest request)
            throws IOException {

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus(),
                        e.getMessage(),
                        request.getRequestURL().toString()),
                e.getStatus());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e, HttpServletRequest request)
            throws IOException {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        request.getRequestURL().toString()),
                status);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(AccessDeniedException e,
            HttpServletRequest request)
            throws IOException {

        HttpStatus status = HttpStatus.FORBIDDEN; // 403

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        "Não podes realizar esta operação",
                        request.getRequestURL().toString()),
                status);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(MethodArgumentNotValidException e,
            HttpServletRequest request)
            throws IOException {

        HttpStatus status = HttpStatus.BAD_GATEWAY;

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        errorMap,
                        request.getRequestURL().toString()),
                status);

    }

}
