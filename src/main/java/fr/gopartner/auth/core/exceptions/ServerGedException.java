package fr.gopartner.auth.core.exceptions;

import fr.gopartner.auth.core.rest.ResponseCode;

public class ServerGedException extends RuntimeException{
    public ServerGedException(ResponseCode responseCode){ super(responseCode.getMessage());}
}
