package me.hanum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExceptionhandlerApplication extends RuntimeException {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionhandlerApplication.class, args);
	}

}
