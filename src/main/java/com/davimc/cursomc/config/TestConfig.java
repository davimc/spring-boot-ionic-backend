package com.davimc.cursomc.config;

import com.davimc.cursomc.services.DBService;
import com.davimc.cursomc.services.EmailService;
import com.davimc.cursomc.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }
}
