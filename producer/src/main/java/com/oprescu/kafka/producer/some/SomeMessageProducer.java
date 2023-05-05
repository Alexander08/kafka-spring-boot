package com.oprescu.kafka.producer.some;

import com.oprescu.kafka.KafkaConfiguration;
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

  private final KafkaTemplate<String, Object> otherKafkaTemplate;


  @Scheduled(fixedRate = 10000)
  public void send() {
    SomeMessage someMessage = new SomeMessage(UUID.randomUUID().toString(), Thread.currentThread().getName(), OffsetDateTime.now());
    log.info(">>>>>>>>>>>>>> SEND MESSAGE: On topic {} with body {}", KafkaConfiguration.SOME_MESSAGE_TOPIC, someMessage);
    otherKafkaTemplate.send(KafkaConfiguration.SOME_MESSAGE_TOPIC, someMessage.id(), someMessage);
  }
}
