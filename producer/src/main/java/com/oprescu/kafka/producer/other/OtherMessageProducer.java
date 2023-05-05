package com.oprescu.kafka.producer.other;

import com.oprescu.kafka.KafkaConfiguration;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtherMessageProducer {

  private final KafkaTemplate<String, Object> otherKafkaTemplate;

  private final AtomicLong atomicLong = new AtomicLong(0);

  @Scheduled(fixedRate = 10000)
  public void send() {

    var message = new OtherMessage(atomicLong.incrementAndGet(), UUID.randomUUID().toString(), OffsetDateTime.now());
    log.info(">>>>>>>>>>>>>> SEND MESSAGE: On topic {} with body {}", KafkaConfiguration.OTHER_MESSAGE_TOPIC, message);
    otherKafkaTemplate.send(KafkaConfiguration.OTHER_MESSAGE_TOPIC, String.valueOf(message.id()), message);
  }
}
