package com.ancapybara.aurre.config;

import com.ancapybara.aurre.domain.mapper.CommentMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean("mapperBean")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("commentMapper")
    public CommentMapper commentMapper() {
        return new CommentMapper();
    }
}
