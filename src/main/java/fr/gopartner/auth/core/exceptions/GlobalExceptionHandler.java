package fr.gopartner.auth.core.exceptions;

import fr.gopartner.auth.core.rest.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//hedha li bsh yhandel ay haja msh meshya shhyha w ykharjelna l message liu nhebou aalih aaleh l fjeya3
public class GlobalExceptionHandler {
    @ExceptionHandler
    // yhandli 3aala 7asb el type (lezem l exception tkoun runtime exeption)
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
