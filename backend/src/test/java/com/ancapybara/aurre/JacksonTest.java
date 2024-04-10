package com.ancapybara.aurre;

import com.ancapybara.aurre.domain.entity.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class JacksonTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void pojoToJsonString() throws JsonProcessingException {
        Post post = new Post("Hello", "Hello world!");
        String json = objectMapper.writeValueAsString(post);
        System.out.println(json);
    }

    @Test
    void pojoArrayToJsonString() throws JsonProcessingException {
        List<Post> arr = new ArrayList<>();
        Post post = new Post("Hello", "Hello world!");
        Post post1 = new Post("Hello world", "Hello world");
        arr.add(post);
        arr.add(post1);
        System.out.println(objectMapper.writeValueAsString(arr));
    }
}
