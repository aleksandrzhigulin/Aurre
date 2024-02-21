package com.ancapybara.aurre.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean("mapperBean")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
