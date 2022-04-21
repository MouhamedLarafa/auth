package fr.gopartner.auth.domain.forgetPassword;

import fr.gopartner.auth.core.exceptions.NotFoundException;
import fr.gopartner.auth.core.rest.ResponseCode;
import fr.gopartner.auth.domain.credentials.Credentials;
import fr.gopartner.auth.domain.forgetPassword.notification.Notification;
import fr.gopartner.auth.domain.forgetPassword.notification.NotificationType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgetPasswordService {
    private final proxy proxy;
    private final ForgetPasswordRepository forgetPasswordRepository;
    //private final CredentialsService credentialsService;

    public ForgetPasswordService(proxy proxy, ForgetPasswordRepository forgetPasswordRepository) {
        this.proxy = proxy;
        this.forgetPasswordRepository = forgetPasswordRepository;
        //this.credentialsService = credentialsService;
    }

    public void forgetPassword(String email) {
       // Credentials credentials = this.credentialsService.findByEmail(email).orElseThrow(() -> new NotFoundException(ResponseCode.PERSON_NOT_FOUND));
        UUID uuid = UUID.randomUUID();
        Notification notification = new Notification();
        notification.setType(NotificationType.EMAIL);
        notification.setDestination(email);
        notification.setBody("Hi,\n" +
                "Forgot your password ?\n" +
                "We received a request to reset the password of your account.\n" +
                "Click to this URL:\n" +"http://localhost:4200/reset-password/"+uuid.toString());
        notification.setSubject("Reset Pasword");
        ForgetPassword token = new ForgetPassword();
        token.setUuid(uuid.toString());
        token.setEmail(email);
        token.setDate(LocalDateTime.now());
        System.err.println(LocalDateTime.now());
        this.forgetPasswordRepository.save(token);
        this.proxy.forgetPassword(notification);


    }

    public Boolean verifyToken(String uuid) {
        Optional<ForgetPassword> tokenResetPassword = this.forgetPasswordRepository.findByUuid(uuid);
        ForgetPassword resetPassword = tokenResetPassword.get();
        LocalDateTime date = LocalDateTime.now();
        long differenceInMinutes = ChronoUnit.MINUTES.between(resetPassword.getDate(), date);
        if (differenceInMinutes < 15) {
            return true;
        } else return false;
    }

    public void resetPwd(String uuid, String password) {
        ForgetPassword forgetPassword = this.forgetPasswordRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(ResponseCode.TOKEN_NOT_FOUND));
      //  this.credentialsService.resetPwd(forgetPassword.getEmail(), password);
        this.forgetPasswordRepository.delete(forgetPassword);
    }
}
