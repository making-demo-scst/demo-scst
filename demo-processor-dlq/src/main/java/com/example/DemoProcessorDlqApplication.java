package com.example;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DemoProcessorDlqApplication {
	private final Logger log = LoggerFactory.getLogger(DemoProcessorDlqApplication.class);

	Random random = new Random();

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	String handle(String text) {
		if (random.nextInt(2) == 1) {
			log.warn("failed!!! => " + text);
			throw new RuntimeException("!!!");
		}
		return "((" + text + "))";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoProcessorDlqApplication.class, args);
	}
}
