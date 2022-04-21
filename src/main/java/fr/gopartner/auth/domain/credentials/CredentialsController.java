package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.PersonsApiDelegate;
import fr.gopartner.auth.model.CredentialsDto;
import fr.gopartner.auth.model.ResetPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CredentialsController implements PersonsApiDelegate {
    private final CredentialsService credentialsService ;

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
        return PersonsApiDelegate.super.getPerson(personId);
    }

    @Override
    public ResponseEntity<Void> resetPassword(Long userId, ResetPassword resetPassword) {
        return PersonsApiDelegate.super.resetPassword(userId, resetPassword);
    }

    @Override
    public ResponseEntity<CredentialsDto> updatePerson(Long personId, CredentialsDto credentialsDto) {
        return PersonsApiDelegate.super.updatePerson(personId, credentialsDto);
    }
}
