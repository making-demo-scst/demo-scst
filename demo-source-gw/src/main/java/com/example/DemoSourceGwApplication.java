package com.example;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
@IntegrationComponentScan
public class DemoSourceGwApplication {

	private final MessageService messageService;

	public DemoSourceGwApplication(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping
	String send(@RequestParam Optional<String> text) {
		messageService.saveMessage(text.orElse("Hello"));
		return "OK";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSourceGwApplication.class, args);
	}
}
