package fr.gopartner.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@Slf4j
public class AuthApplication {

	public static void main(String[] args) {
		log.info("application started !");
		SpringApplication.run(AuthApplication.class, args);
	}

}
