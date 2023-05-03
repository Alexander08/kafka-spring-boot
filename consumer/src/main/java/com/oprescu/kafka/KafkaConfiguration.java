package com.oprescu.kafka;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class KafkaConfiguration {


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
  public MappingJackson2MessageConverter mappingJackson2MessageConverter(ObjectMapper objectMapper) {
    DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
    resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setContentTypeResolver(resolver);
    converter.setObjectMapper(objectMapper);
    return converter;
  }

  @Bean
  public NewTopic someMessageTopic() {
    return TopicBuilder.name("some-message-topic")
        .partitions(4)
        .replicas(1)
        .build();
  }

}
