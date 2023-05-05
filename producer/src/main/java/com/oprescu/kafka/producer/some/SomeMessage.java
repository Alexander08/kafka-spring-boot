package com.oprescu.kafka.producer.some;

import java.time.OffsetDateTime;

public record SomeMessage(String id, String name, OffsetDateTime offsetDateTime) {

}
