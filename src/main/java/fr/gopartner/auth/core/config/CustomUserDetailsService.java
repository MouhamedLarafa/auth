package fr.gopartner.auth.core.config;

import fr.gopartner.auth.domain.credentials.CredentialsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;

    public CustomUserDetailsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return credentialsRepository.findByEmail(email)
                .map(credentials -> new User(credentials.getEmail(),credentials.getPassword(),credentials.getEnabled(),true,true,true,credentials.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException("Username " + email + " not found"));
    }

}

