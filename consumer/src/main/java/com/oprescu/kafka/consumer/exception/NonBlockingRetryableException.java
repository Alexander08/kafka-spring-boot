package com.oprescu.kafka.consumer.exception;

public class NonBlockingRetryableException extends RuntimeException {

  public NonBlockingRetryableException(String message) {
    super(message);
  }
}
