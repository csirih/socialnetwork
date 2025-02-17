package com.socialnetwork.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class AppConfiguration {
    @Value("${mangodb.connection}")
    private String CONNECTION_STRING;

    @Bean
    public MongoClient getMongoClient() {
     return MongoClients.create(CONNECTION_STRING);
    }
}
