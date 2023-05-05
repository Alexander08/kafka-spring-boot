package com.oprescu.kafka.consumer.exception;

public class NotRetryableException extends RuntimeException {

  public NotRetryableException(String message) {
    super(message);
  }
}
