package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DemoProcessorTransformerApplication {

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String handle(String text) {
		return "<<" + text + ">>";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoProcessorTransformerApplication.class, args);
	}
}
