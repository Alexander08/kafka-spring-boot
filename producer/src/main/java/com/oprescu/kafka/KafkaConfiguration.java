package com.oprescu.kafka;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Slf4j
@Configuration
public class KafkaConfiguration {

  public static final String SOME_MESSAGE_TOPIC = "some-message-topic";

  public static final String OTHER_MESSAGE_TOPIC = "other-message-topic";


  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    return objectMapper;
  }

  @Bean
  public NewTopic someMessageTopic() {
    return TopicBuilder.name(SOME_MESSAGE_TOPIC)
        .partitions(4)
        .replicas(1)
        .build();
  }

  @Bean
  public ProducerFactory<String, Object> otherProducerFactory(ObjectMapper objectMapper) {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    JsonSerializer<Object> objectSerializer = new JsonSerializer<>(objectMapper);

    return new DefaultKafkaProducerFactory<>(props, null, objectSerializer);
  }

  @Bean
  public KafkaTemplate<String, Object> otherKafkaTemplate(ProducerFactory<String, Object> otherProducerFactory) {
    return new KafkaTemplate<>(otherProducerFactory);
  }
}
