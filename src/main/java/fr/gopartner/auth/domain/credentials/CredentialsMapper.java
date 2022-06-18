package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.domain.authority.AuthorityMapper;
import fr.gopartner.auth.model.CredentialsDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CredentialsMapper {

    private final AuthorityMapper authorityMapper;

    public CredentialsMapper(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    public Credentials toPerson(CredentialsDto credentialsDto) {
        if (credentialsDto == null) {
            return null;
        }

        return Credentials.builder()
                .id(credentialsDto.getId())
                .email(credentialsDto.getEmail())
                .password(credentialsDto.getPassword())
                .authorities(credentialsDto.getRole().stream().map(authorityMapper::toAuthority).collect(Collectors.toSet()))
                .lastName(credentialsDto.getLastName())
                .name(credentialsDto.getName())
                .enabled(credentialsDto.getEnabled())
                .build();
    }

    public CredentialsDto toDto(Credentials credentials) {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setId(credentials.getId());
        credentialsDto.setEmail(credentials.getEmail());
        credentialsDto.setEnabled(credentials.getEnabled());
        credentialsDto.setLastName(credentials.getLastName());
        credentialsDto.setName(credentials.getName());
        credentialsDto.setPassword(credentials.getPassword());
        credentialsDto.setRole(credentials.getAuthorities().stream().map(authorityMapper::toDto).collect(Collectors.toList()));
        return credentialsDto;
    }
}
