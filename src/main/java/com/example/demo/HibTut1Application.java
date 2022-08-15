package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class HibTut1Application {

	@ExceptionHandler(UserNotFoundException.class)
	public static void main(String[] args) {
		SpringApplication.run(HibTut1Application.class, args);
	}

}
