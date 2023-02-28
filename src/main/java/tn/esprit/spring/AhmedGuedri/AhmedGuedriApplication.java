package tn.esprit.spring.AhmedGuedri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.AhmedGuedri.Services.WebSecurityConfig;


@EnableScheduling
@SpringBootApplication
public class AhmedGuedriApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhmedGuedriApplication.class, args);
	}
}


