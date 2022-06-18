package fr.gopartner.auth.core.exceptions;

import fr.gopartner.auth.core.rest.ResponseCode;

public class NotFoundException extends RuntimeException{
    public NotFoundException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }
}
