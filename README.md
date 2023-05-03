# Kafka with spring boot

This repository contains:

- consumer - a project that consumes messages from kafka topic
- producer - a project that produces messages to a kafka topic

And a docker compose file to configure zookeeper and kafka.

For checking the messages kafka-ui is used.

## Run locally

1. Start docker compose with: 
```shell
docker-compose up --build
```

2. The UI for Kafka is now available at : http://localhost:9090

3. Build and start the producer and the consumer project
```shell
./gradlew clean build bootRun
```


## Kafka versions and compatibility matrix

This section contains links about kafka client, broker and spring boot compatibility

### Kafka client

The client - broker protocol-compatibility is assured [according to Kafka](https://kafka.apache.org/protocol.html#protocol_compatibility)

### Kafka for Spring

For Spring boot and kafka-client version compatibility [check this page](https://spring.io/projects/spring-kafka)

### Docker images version mapping

kafka-confluent and kafka
versions [can be checked here](https://docs.confluent.io/platform/current/installation/versions-interoperability.html#cp-and-apache-ak-compatibility)

### Broker Compatibility matrix

For more info [check this page](https://cwiki.apache.org/confluence/display/KAFKA/Compatibility+Matrix)

## UI tools for monitor and management

[This article](https://towardsdatascience.com/overview-of-ui-tools-for-monitoring-and-management-of-apache-kafka-clusters-8c383f897e80)
contains a detailed example of kafka managers