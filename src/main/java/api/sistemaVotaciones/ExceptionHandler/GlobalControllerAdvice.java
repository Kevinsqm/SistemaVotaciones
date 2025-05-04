package api.sistemaVotaciones.ExceptionHandler;

import api.sistemaVotaciones.ErrorModels.ErrorResponse;
import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.exceptions.InvalidEntityException;
import api.sistemaVotaciones.exceptions.UnauthenticatedException;
import api.sistemaVotaciones.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Resource not found");
        error.setStatus(404);
        error.setDetail(ex.getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(InvalidEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidEntityException(InvalidEntityException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Invalid entity");
        error.setStatus(400);
        error.setDetail(ex.getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Invalid fields");
        error.setStatus(400);
        error.setDetail("One or more fields are invalid");
        error.setErrors(errors);
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Invalid argument type");
        error.setStatus(400);
        error.setDetail(ex.getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Invalid data access");
        error.setStatus(400);
        error.setDetail(ex.getCause().getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthenticatedException(UnauthenticatedException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Unauthenticated");
        error.setStatus(401);
        error.setDetail(ex.getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Unauthorized");
        error.setStatus(403);
        error.setDetail(ex.getMessage());
        error.setInstance(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setTitle("Internal server error");
        error.setStatus(500);
        error.setDetail(ex.toString());
        error.setInstance(request.getRequestURI());
        return error;
    }
}
