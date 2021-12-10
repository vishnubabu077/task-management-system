package com.task.logger.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main class for Task Management logger
 * 
 * @author Vishnu
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.task.logger")
@EnableJpaRepositories("com.task.logger")
@EntityScan("com.task.logger")
@ConfigurationPropertiesScan("com.test")
public class TaskLogger {

	public static void main(String[] args) {
		SpringApplication.run(TaskLogger.class, args);
	}

	// TODO configure for CORS
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("POST", "PUT", "GET", "DELETE");
			}
		};
	}

}
