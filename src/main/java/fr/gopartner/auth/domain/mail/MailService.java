package fr.gopartner.auth.domain.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleEmail(Mail notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("larafamouhamed5@gmail.com");
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());
        message.setTo(notification.getDestination());
        javaMailSender.send(message);
    }

}
