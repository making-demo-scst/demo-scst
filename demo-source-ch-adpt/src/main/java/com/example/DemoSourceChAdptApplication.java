package com.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.SourcePollingChannelAdapterFactoryBean;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.support.PeriodicTrigger;

@SpringBootApplication
@EnableBinding(Source.class)
public class DemoSourceChAdptApplication {

	private final Source source;

	public DemoSourceChAdptApplication(Source source) {
		this.source = source;
	}

	@Bean
	SourcePollingChannelAdapterFactoryBean channelAdapter() {
		AtomicInteger counter = new AtomicInteger(1);
		MessageSource<String> source = () -> MessageBuilder
				.withPayload("Hello " + counter.getAndIncrement()).build();
		PollerMetadata metadata = new PollerMetadata();
		metadata.setTrigger(new PeriodicTrigger(1, TimeUnit.SECONDS));

		SourcePollingChannelAdapterFactoryBean factoryBean = new SourcePollingChannelAdapterFactoryBean();
		factoryBean.setOutputChannel(this.source.output());
		factoryBean.setSource(source);
		factoryBean.setPollerMetadata(metadata);
		return factoryBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSourceChAdptApplication.class, args);
	}
}
