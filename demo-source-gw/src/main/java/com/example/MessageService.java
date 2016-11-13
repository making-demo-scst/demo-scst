package com.example;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageService {
	@Gateway(requestChannel = Source.OUTPUT)
	void saveMessage(String text);
}
