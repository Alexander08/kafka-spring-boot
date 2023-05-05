package com.oprescu.kafka.consumer.exception;

public class BlockingRetryableException extends RuntimeException {

  public BlockingRetryableException(String message) {
    super(message);
  }
}
