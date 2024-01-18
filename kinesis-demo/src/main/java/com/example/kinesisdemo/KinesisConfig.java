package com.example.kinesisdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;

@Configuration
@ConditionalOnProperty("aws.kinesis.streamName")
public class KinesisConfig {
    // ... (existing configuration)


    @Value("${aws.kinesis.region}")
    private Region region;

    @Bean
    public KinesisClient kinesisClient() {
        System.out.println("Creating KinesisClient bean...");
        return KinesisClient.builder()
                .region(region)
                .build();
    }

}
