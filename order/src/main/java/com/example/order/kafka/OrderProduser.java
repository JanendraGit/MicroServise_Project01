package com.example.order.kafka;

import com.base.base.dto.OrderEventDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProduser {

    private static final Logger log = LoggerFactory.getLogger(OrderEventDto.class);

    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderEventDto> kafkaTemplate;

    public OrderProduser(NewTopic newTopic, KafkaTemplate<String, OrderEventDto> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEventDto orderEventDto) {
        log.info("Sending order event to topic {}", orderEventDto.toString());

        Message<OrderEventDto> message = MessageBuilder
                .withPayload(orderEventDto)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
