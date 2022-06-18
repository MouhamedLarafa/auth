package fr.gopartner.auth.core.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
// hedha l objet li yraja3 fyh l handler
public class ServerResponse {
    private String message;

}

