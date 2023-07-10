package com.ancapybara.aurre.Post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class PostController {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/get/all")
    public ResponseEntity<String> getAllPosts() throws JsonProcessingException {
        Iterable<Post> posts = postRepository.findAll();
        String json = objectMapper.writeValueAsString(posts);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/posts/get/{id}")
    public ResponseEntity<String> getPostById(@PathVariable Long id) throws JsonProcessingException {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            String json = objectMapper.writeValueAsString(post);
            return ResponseEntity.ok(json);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/posts/create")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) throws JsonProcessingException {
        String title = postRequest.getTitle();
        String content = postRequest.getContent();
        String author = postRequest.getAuthor();
        Post post = postRepository.save(new Post(title, content, author));
        String json = objectMapper.writeValueAsString(post);
        return ResponseEntity.ok(json);
    }
}
