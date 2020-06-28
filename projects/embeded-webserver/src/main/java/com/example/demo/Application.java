package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.eclipse.jetty.server.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/hello")
	public String hello(){
		return "Hello spring";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
