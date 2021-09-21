package com.soliva.os.config;

import com.soliva.os.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig {

    private DBService dbService;

    @Autowired
    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void instanciaDB() {
        dbService.instanciaDB();
    }
}
