package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.model.AuthorityDto;
import fr.gopartner.auth.model.CredentialsDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper ;
    private final CredentialsRepository credentialsRepository ;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public CredentialsService(CredentialsMapper credentialsMapper, CredentialsRepository credentialsRepository) {
        this.credentialsMapper = credentialsMapper;
        this.credentialsRepository = credentialsRepository;
    }

    public CredentialsDto create(CredentialsDto credentialsDto){
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(1L);
        authorityDto.setAuthority("ROLE_CLIENT");
        credentialsDto.setEnabled(true);
        credentialsDto.setRole(Arrays.asList(authorityDto));
        Credentials credentialsToCreate = credentialsMapper.toPerson(credentialsDto);
        credentialsToCreate.setPassword(bCryptPasswordEncoder.encode(credentialsDto.getPassword()));
        Credentials credentials =credentialsRepository.save(credentialsToCreate);
        CredentialsDto credentialsDtoToReturn = credentialsMapper.toDto(credentials);
        credentialsDtoToReturn.setPassword(credentialsDto.getPassword());
        return credentialsDtoToReturn;
    }
}
