package fr.gopartner.auth.core.exceptions;

import fr.gopartner.auth.core.rest.ResponseCode;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException(ResponseCode responseCode){
        super(responseCode.getMessage());
    }
}
