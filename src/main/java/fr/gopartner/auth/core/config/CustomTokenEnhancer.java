package fr.gopartner.auth.core.config;

import fr.gopartner.auth.domain.credentials.CredentialsRepository;
import fr.gopartner.auth.domain.credentials.Credentials;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    private final CredentialsRepository repository;

    public CustomTokenEnhancer(CredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    // hedha li bsh yasn3elna el token
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        Authentication customAuthentication = authentication.getUserAuthentication();
        if (customAuthentication == null) {
            return accessToken;
        }
        Map<String, Object> details = ( Map<String, Object>) customAuthentication.getDetails();
        if (details == null) {
            return accessToken;
        }
        String username = (String)details.get("username");
        Credentials credentials = repository.findByEmail(username).get();
        System.err.println(credentials);
        additionalInfo.put("id", credentials.getId());
        additionalInfo.put("role", credentials.getAuthorities());
        additionalInfo.put("enabled", credentials.getEnabled());
        additionalInfo.put("firstName", credentials.getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
