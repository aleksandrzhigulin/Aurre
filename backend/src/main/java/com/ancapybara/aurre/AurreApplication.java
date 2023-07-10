package com.ancapybara.aurre;

import com.ancapybara.aurre.Security.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
public class AurreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AurreApplication.class, args);
	}

}
