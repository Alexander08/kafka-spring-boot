package com.oprescu.kafka.consumer.some;

import com.oprescu.kafka.KafkaConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SomeMessageListener {

  @KafkaListener(
      id = "some-messages-listener",
      topics = KafkaConfiguration.SOME_MESSAGE_TOPIC,
      groupId = KafkaConfiguration.GROUP_ID,
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(SomeMessage someMessage) {
    log.info("########### RECEIVED SomeMessage {}", someMessage);
  }

}
