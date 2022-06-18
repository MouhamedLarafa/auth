package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.PersonsApiDelegate;
import fr.gopartner.auth.model.CredentialsDto;
import fr.gopartner.auth.model.ResetPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class CredentialsController implements PersonsApiDelegate {
    private final CredentialsService credentialsService;

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @Override
    public ResponseEntity<CredentialsDto> createPerson(CredentialsDto credentialsDto) {
        return new ResponseEntity<>(credentialsService.create(credentialsDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePerson(Long personId) {
        return PersonsApiDelegate.super.deletePerson(personId);
    }

    @Override
    public ResponseEntity<List<CredentialsDto>> getPeople() {
        return PersonsApiDelegate.super.getPeople();
    }

    @Override
    public ResponseEntity<CredentialsDto> getPerson(Long personId) {
        return new ResponseEntity<>(credentialsService.getById(personId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> resetPassword(Long userId, ResetPassword resetPassword) {
        credentialsService.resetPassword(userId, resetPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CredentialsDto> updatePerson(Long personId, CredentialsDto credentialsDto) {
        return PersonsApiDelegate.super.updatePerson(personId, credentialsDto);
    }

    @GetMapping("/me")
    public Principal me(Principal principal) {
        return credentialsService.getPrincipalWithClaims();
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<OAuth2AccessToken> deleteToken(@RequestHeader("Authorization") String token) {
        try {
            credentialsService.deleteToken(token.substring(7));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/forgotPassword/{email}")
    public ResponseEntity<Void> forgotPassword(@PathVariable String email){
        credentialsService.forgotPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

