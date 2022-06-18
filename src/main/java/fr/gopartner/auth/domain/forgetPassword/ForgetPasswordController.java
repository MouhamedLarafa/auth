package fr.gopartner.auth.domain.forgetPassword;

import fr.gopartner.auth.ForgetPasswordApiDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class ForgetPasswordController implements ForgetPasswordApiDelegate {
    private final ForgetPasswordService forgetPasswordService;

    public ForgetPasswordController(ForgetPasswordService forgetPasswordService) {
        this.forgetPasswordService = forgetPasswordService;
    }

    @Override
    public ResponseEntity<Void> forgetPasswordUsingEmail(String email) {
        try {
            this.forgetPasswordService.forgetPassword(email);
            log.info("An email has been sent");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Void> verifierToken(String uuid) {
        try {
            Boolean verify = this.forgetPasswordService.verifyToken(uuid);
            if (verify) {
                log.info("Token is valid");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("Token is expired");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            log.info("token not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> resetPwd(String uuid, String body) {
        this.forgetPasswordService.resetPwd(uuid, body);
        log.info("Password has been changed");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
