package fr.gopartner.auth.core.exceptions;

import fr.gopartner.auth.core.rest.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ServerResponse> handleNoTFoundException(NotFoundException exception){
        ServerResponse serverResponse = ServerResponse.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(serverResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handlePasswordDoesMatchException(PasswordDoesNotMatchException exception){
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }
}
