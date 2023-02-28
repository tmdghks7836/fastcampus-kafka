package com.fastcampus.chap1;

import com.fastcampus.chap1.model.Animal;
import com.fastcampus.chap1.producer.ClipProducer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Chap1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer) {

        return args -> {

            clipProducer.async("clip4-animal", new Animal("원숭이", 9));
        };
    }
}
