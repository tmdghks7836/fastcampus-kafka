package com.fastcampus.chap1.config;

import com.fastcampus.chap1.consumer.listener.DefaultMessageListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

public class MessageListenerContainerConfiguration {

//    @Bean
    public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {

        ContainerProperties containerProperties = new ContainerProperties("clip4");
        containerProperties.setGroupId("clip4-container");
        containerProperties.setAckMode(ContainerProperties.AckMode.BATCH);
        containerProperties.setMessageListener(new DefaultMessageListener()); //컨테이너에 메세지 리스너가 등록되어있지않으면 동작하지 않음

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties);
        container.setAutoStartup(false);


        return container;
    }

    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(props());
    }

    private Map<String, Object> props() {

        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return map;
    }
}
