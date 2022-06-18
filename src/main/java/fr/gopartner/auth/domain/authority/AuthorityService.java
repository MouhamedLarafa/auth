package fr.gopartner.auth.domain.authority;

import fr.gopartner.auth.model.AuthorityDto;
import org.springframework.stereotype.Service;
import fr.gopartner.auth.core.exceptions.NotFoundException;
import fr.gopartner.auth.core.rest.ResponseCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
        this.authorityRepository = authorityRepository;
    }


    public List<AuthorityDto> getAll() {
        List<Authority> roleList = authorityRepository.findAll();
        return roleList.stream().map(authorityMapper::toDto).collect(Collectors.toList());
    }

    public AuthorityDto getById(Long id) {
        AuthorityDto authority = authorityMapper.toDto(this.getAuthorityById(id));
        return authority;
    }

    public AuthorityDto create(AuthorityDto roleDto) {
        Authority authority = authorityRepository.save(authorityMapper.toAuthority(roleDto));
        return authorityMapper.toDto(authority);
    }

    public AuthorityDto update(AuthorityDto roleDto) {
        Authority authority = this.getAuthorityById(roleDto.getId());
        authority.setAuthority(roleDto.getAuthority());
        Authority authUpdated = authorityRepository.save(authority);
        return authorityMapper.toDto(authUpdated);
    }

    public void delete(Long id) {
        Authority authority = this.getAuthorityById(id);
        authorityRepository.delete(authority);
    }

    private Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.ROLE_NOT_FOUND));
    }

}
