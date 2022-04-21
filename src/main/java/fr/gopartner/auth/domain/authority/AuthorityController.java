package fr.gopartner.auth.domain.authority;

import fr.gopartner.auth.RolesApiDelegate;
import fr.gopartner.auth.model.AuthorityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AuthorityController implements RolesApiDelegate {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    public ResponseEntity<AuthorityDto> createRole(AuthorityDto role) {
        authorityService.create(role);
        log.info("authority created !");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteRole(Long roleId) {
        authorityService.delete(roleId);
        log.info("authority deleted !");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorityDto> getRole(Long roleId) {
        AuthorityDto role = authorityService.getById(roleId);
        log.info("get authority !");
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AuthorityDto>> getRoles() {
        List<AuthorityDto> roleList = authorityService.getAll();
        log.info("get all authority !");
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorityDto> updateRole(AuthorityDto role) {
        AuthorityDto roleUpdated = authorityService.update(role);
        log.info("authority updated !");
        return new ResponseEntity<>(roleUpdated, HttpStatus.ACCEPTED);
    }
}

