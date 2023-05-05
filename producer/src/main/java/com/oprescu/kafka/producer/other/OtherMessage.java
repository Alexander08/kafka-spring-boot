package com.oprescu.kafka.producer.other;

import java.time.OffsetDateTime;

public record OtherMessage(Long id, String name, OffsetDateTime dateTime) {

}
