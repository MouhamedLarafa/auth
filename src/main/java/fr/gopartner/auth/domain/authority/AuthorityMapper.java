package fr.gopartner.auth.domain.authority;

import fr.gopartner.auth.model.AuthorityDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorityMapper {
    public AuthorityDto toDto(Authority authority) {
        if (authority == null) {
            return null;
        }
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setAuthority(authority.getAuthority());
        return authorityDto;
    }

    public Authority toAuthority(AuthorityDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        return Authority.builder()
                .id(roleDto.getId())
                .authority(roleDto.getAuthority())
                .build();
    }

}
