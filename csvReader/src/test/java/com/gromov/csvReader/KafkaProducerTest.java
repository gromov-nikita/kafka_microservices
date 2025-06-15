package com.gromov.csvReader;

import com.gromov.csvReader.service.kafka.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaProducerService kafkaProducerService;

    private static final String topic = "my-topic";

    private static final String message = "test-message";

    @BeforeEach
    void setUp() {
        kafkaProducerService = new KafkaProducerService(kafkaTemplate);
    }

    @Test
    void testSendCallsKafkaTemplate() {

        kafkaProducerService.send(topic, message);

        verify(kafkaTemplate).send(topic, message);
    }
}
