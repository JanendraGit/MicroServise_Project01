package com.example.inventory.kafka;

import com.base.base.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderEventDto.class);

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "inventory"
    )

    public void consume(OrderEventDto dto) {
        log.info("Receive Order Event: {}", dto);
    }
}
