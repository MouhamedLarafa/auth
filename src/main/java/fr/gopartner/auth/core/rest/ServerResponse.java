package fr.gopartner.auth.core.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ServerResponse {
    private String message;

}

