package fr.gopartner.auth.domain.credentials;

import fr.gopartner.auth.domain.mail.Mail;
import fr.gopartner.auth.domain.mail.MailService;
import fr.gopartner.auth.model.AuthorityDto;
import fr.gopartner.auth.model.CredentialsDto;
import fr.gopartner.auth.core.exceptions.NotFoundException;
import fr.gopartner.auth.core.rest.ResponseCode;
import fr.gopartner.auth.model.ResetPassword;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final CredentialsRepository credentialsRepository;
    private final MailService mailService;
    private final TokenStore tokenStore;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public CredentialsService(MailService mailService,CredentialsMapper credentialsMapper, TokenStore tokenStore, CredentialsRepository credentialsRepository) {
        this.credentialsMapper = credentialsMapper;
        this.tokenStore = tokenStore;
        this.credentialsRepository = credentialsRepository;
        this.mailService=mailService;
    }

    public CredentialsDto create(CredentialsDto credentialsDto) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(1L);
        authorityDto.setAuthority("ROLE_CLIENT");
        credentialsDto.setEnabled(true);
        credentialsDto.setRole(Arrays.asList(authorityDto));
        Credentials credentialsToCreate = credentialsMapper.toPerson(credentialsDto);
        credentialsToCreate.setPassword(bCryptPasswordEncoder.encode(credentialsDto.getPassword()));
        Credentials credentials = credentialsRepository.save(credentialsToCreate);
        CredentialsDto credentialsDtoToReturn = credentialsMapper.toDto(credentials);
        credentialsDtoToReturn.setPassword(credentialsDto.getPassword());
        return credentialsDtoToReturn;
    }

    public void resetPassword(String password,Long id){
        Credentials credentials = credentialsRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.PERSON_NOT_FOUND));
        credentials.setPassword(bCryptPasswordEncoder.encode(password));
        credentialsRepository.save(credentials);
    }

    public CredentialsDto getById(Long id) {
        return credentialsMapper.toDto(this.credentialsRepository.findById(id).orElseThrow(() -> new NotFoundException(ResponseCode.PERSON_NOT_FOUND)));
    }

    private Map<String, Object> getAdditionalInfo(Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }

    public OAuth2Authentication getPrincipalWithClaims() {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        if (oAuth2Authentication.getUserAuthentication() != null
                && oAuth2Authentication.getUserAuthentication().getPrincipal() != null
                && oAuth2Authentication.getUserAuthentication().getPrincipal() instanceof UserDetails) {
            Map<String, Object> additionalInfo = getAdditionalInfo(oAuth2Authentication);
            oAuth2Authentication.setDetails(additionalInfo);
        }
        return oAuth2Authentication;
    }

    public void deleteToken(String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
    }

    public void forgotPassword(String email) {
        Credentials credentials = credentialsRepository.findByEmail(email).orElseThrow(()->new NotFoundException(ResponseCode.PERSON_NOT_FOUND));
        Mail mail = new Mail();
        mail.setDestination(credentials.getEmail());
        mail.setSubject("Forgot password");
        mail.setMessage(
                "Salut cher(e)"+credentials.getName()+
                        "\n Cliquer sur le lien ci-dessous pour re-initialiser votre mot de passe \n" +
                        "http://localhost:4200/resetPassword/"+credentials.getId()
        );
        mailService.sendSimpleEmail(mail);

    }

    public void resetPassword(Long id, ResetPassword resetPassword){
        System.err.println("f methode");
        Credentials credentials = credentialsRepository.findById(id).orElseThrow(()->new NotFoundException(ResponseCode.PERSON_NOT_FOUND));
        credentials.setPassword(
                bCryptPasswordEncoder.encode
                        (resetPassword.getNewPassword()));
        credentialsRepository.save(credentials);
    }
}
