package com.oprescu.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SomeMessageListener {

  @KafkaListener(
      id = "some-messages-listener",
      topics = "some-message-topic",
      groupId = "consumer-1",
      contentTypeConverter = "mappingJackson2MessageConverter")
  public void listen(SomeMessage someMessage) {
    log.info("########### RECEIVED SomeMessage {}", someMessage);
  }

}
