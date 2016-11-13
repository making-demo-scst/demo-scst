package com.example;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableBinding(Source.class)
public class DemoSourceMsApplication {

	private final Source source;

	public DemoSourceMsApplication(Source source) {
		this.source = source;
	}

	@Bean
	@InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
	MessageSource<String> source() {
		AtomicInteger counter = new AtomicInteger(1);
		return () -> MessageBuilder.withPayload("Hello " + counter.getAndIncrement())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSourceMsApplication.class, args);
	}
}
