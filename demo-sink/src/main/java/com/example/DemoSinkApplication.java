package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableBinding(Sink.class)
public class DemoSinkApplication {

	private final Sink sink;

	public DemoSinkApplication(Sink sink) {
		this.sink = sink;
	}

	@StreamListener(Sink.INPUT)
	void receive(Message<String> message) {
		System.out.println("Received " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSinkApplication.class, args);
	}
}
