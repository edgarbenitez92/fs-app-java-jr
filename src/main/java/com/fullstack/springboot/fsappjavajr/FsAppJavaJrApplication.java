package com.fullstack.springboot.fsappjavajr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FsAppJavaJrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsAppJavaJrApplication.class, args);
	}

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// return new WebMvcConfigurer() {
	// public void addCorsMapping(CorsRegistry registry) {
	// registry.addMapping("/**")
	// .allowedMethods("*")
	// .allowedOrigins("http://localhost:5050");
	// }
	// };
	// }

}
