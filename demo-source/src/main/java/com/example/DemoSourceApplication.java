package com.example;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class DemoSourceApplication {

	private final Source source;

	public DemoSourceApplication(Source source) {
		this.source = source;
	}

	@GetMapping
	String send(@RequestParam Optional<String> text) {
		source.output().send(MessageBuilder.withPayload(text.orElse("Hello")).build());
		return "OK";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSourceApplication.class, args);
	}
}
