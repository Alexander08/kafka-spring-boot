package com.oprescu.kafka.consumer.other;

import java.time.OffsetDateTime;

public record OtherMessage(Long id, String name, OffsetDateTime dateTime) {

}
