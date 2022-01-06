package com.example.tomcatlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TomcatLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomcatLogApplication.class, args);
	}

	@GetMapping("/hello/{name}")
	public String sayHello(@PathVariable String name) {
		return "Hello " + name;
	}

	@GetMapping("/goodbye/{name}")
	public String sayGoodBye(@PathVariable String name) {
		return "Good bye " + name;
	}
}
