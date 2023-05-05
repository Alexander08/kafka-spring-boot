package com.oprescu.kafka.consumer.some;

import java.time.OffsetDateTime;

public record SomeMessage(String id, String name, OffsetDateTime offsetDateTime) {

}
