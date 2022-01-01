package SWProject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions(Exception ex, WebRequest request){
        ErrorDetails details = new ErrorDetails(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_ACCEPTABLE);
    }
}
