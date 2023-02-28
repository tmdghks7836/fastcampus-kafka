package com.fastcampus.chap1;

import com.fastcampus.chap1.service.ClipConsumer;
import com.fastcampus.chap1.service.KafkaManger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Chap1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaManger kafkaManager,
                                    KafkaTemplate<String, String> kafkaTemplate,
                                    ClipConsumer clipConsumer) {

        return args -> {
            kafkaManager.describeTopicConfigs();
            kafkaManager.changeConfig();
            kafkaManager.findAllConsumerGroups();
            kafkaManager.findAllOffset();
            kafkaTemplate.send("clip1-listener", "Hello, Listener.");
            clipConsumer.seek();
        };
    }


}
