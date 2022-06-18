package fr.gopartner.auth.domain.mail;

import lombok.Data;

@Data
public class Mail {

    private String subject;
    private String destination;
    private String message;
}
