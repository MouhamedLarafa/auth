package fr.gopartner.auth.domain.forgetPassword.notification;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Notification {
    @Id
    private Long id;
    private String destination;
    private NotificationType type;
    private String body;
    private String sender;
    private String subject;
}
