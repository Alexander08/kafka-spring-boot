package com.oprescu.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SomeMessageProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;


  @Scheduled(fixedRate = 10000)
  public void send() throws JsonProcessingException {
    SomeMessage someMessage = new SomeMessage(UUID.randomUUID().toString(), Thread.currentThread().getName(), OffsetDateTime.now());
    String jsonMessage = objectMapper.writeValueAsString(someMessage);
    log.info(">>>>>>>>>>>>>> SEND MESSAGE: On topic {} with body {}", KafkaConfiguration.SOME_MESSAGE_TOPIC, jsonMessage);
    kafkaTemplate.send(KafkaConfiguration.SOME_MESSAGE_TOPIC, someMessage.id(), jsonMessage);
  }
}
