package com.oprescu.kafka;

import java.time.OffsetDateTime;

public record SomeMessage(String id, String name, OffsetDateTime offsetDateTime) {

}
