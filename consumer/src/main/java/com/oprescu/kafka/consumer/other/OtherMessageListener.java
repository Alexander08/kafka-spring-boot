package com.oprescu.kafka.consumer.other;

import com.oprescu.kafka.KafkaConfiguration;
import com.oprescu.kafka.consumer.exception.BlockingRetryableException;
import com.oprescu.kafka.consumer.exception.NonBlockingRetryableException;
import com.oprescu.kafka.consumer.exception.NotRetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtherMessageListener {

  @RetryableTopic(
      backoff = @Backoff(delayExpression = "10000", multiplierExpression = "2"),
      include = {NonBlockingRetryableException.class, BlockingRetryableException.class},
      timeout = "10000",
      topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
  @KafkaListener(
      topics = KafkaConfiguration.OTHER_MESSAGE_TOPIC,
      groupId = KafkaConfiguration.GROUP_ID,
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(OtherMessage otherMessage) {

    log.info("########### RECEIVED OtherMessage {}", otherMessage);

    if (otherMessage.id() % 3 == 0) {

      log.error("----------- Throw new BlockingRetryableException error for message {}", otherMessage);
      throw new BlockingRetryableException("Test BlockingRetryableException");
    }

    if (otherMessage.id() % 7 == 0) {
      log.error("-----------  Throw new NonBlockingRetryableException error for message {}", otherMessage);
      throw new NonBlockingRetryableException("Test NonBlockingRetryableException");
    }

    if (otherMessage.id() % 8 == 0) {
      log.error("-----------  Throw new NotRetryableException error for message {}", otherMessage);
      throw new NotRetryableException("Test NotRetryableException");
    }

  }

  @DltHandler()
  public void dlt(String data, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    log.warn("Event from topic {} is dead lettered - event: {}", topic, data);
  }
}
