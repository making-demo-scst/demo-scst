package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DemoProcessorRxApplication {

	@StreamListener
	@Output(Processor.OUTPUT)
	public Flux<String> handle(@Input(Processor.INPUT) Flux<String> stream) {
		return stream.log("log.input").map(text -> "{{" + text + "}}").log("log.output");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoProcessorRxApplication.class, args);
	}
}
