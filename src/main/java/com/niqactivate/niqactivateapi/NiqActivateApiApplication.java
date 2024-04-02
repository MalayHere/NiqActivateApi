package com.niqactivate.niqactivateapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NiqActivateApiApplication {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(NiqActivateApiApplication.class, args);
    }

}
