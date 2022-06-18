package fr.gopartner.auth.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityServer extends WebSecurityConfigurerAdapter {

    private static final String[] authIgnore = {"/swagger-ui.html"};
    private UserDetailsService userDetailsService;
    //private CorsConfiguration corsConfiguration;

    public WebSecurityServer(CustomUserDetailsService userDetailsService) {
      //  this.corsConfiguration = cors;
        this.userDetailsService = userDetailsService;
    }

    @Override
    //fama des path ma7loulin meghyr authentification w wahdin le
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:offs
        http
               // .addFilterBefore(corsConfiguration, ChannelProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        // @formatter:on
    }
   @Override
   // ignore les paths li ktebnehom lenna
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(authIgnore);
    }

    @Override
    //hedha li yaaaml authentification mta3 l user
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
