package com.ancapybara.aurre.Post;

import com.ancapybara.aurre.Services.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("postControllerClass")
@CrossOrigin(origins = "http://localhost:8081")
public class PostController {
  private final PostService postService;

  private final ObjectMapper objectMapper;


  @GetMapping("/posts/get/all")
  public ResponseEntity<String> getAllPosts() throws JsonProcessingException {
    Iterable<Post> posts = postService.getAllPosts();
    String json = objectMapper.writeValueAsString(posts);
    return ResponseEntity.ok(json);
  }

  @GetMapping("/posts/get/author/{author}")
  public ResponseEntity<?> getAllPostsByAuthor(@PathVariable("author") String author)
      throws JsonProcessingException {
    Iterable<Post> posts = postService.getAllPostsByAuthor(author);
    String json = objectMapper.writeValueAsString(posts);
    return ResponseEntity.ok(json);
  }

  @GetMapping("/posts/get/{id}")
  public ResponseEntity<String> getPostById(@PathVariable Long id) throws JsonProcessingException {
    Optional<Post> post = postService.getPostById(id);
    if (post.isPresent()) {
      String json = objectMapper.writeValueAsString(post);
      return ResponseEntity.ok(json);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/posts/create")
  public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest)
      throws JsonProcessingException {

    Post post = postService.createPost(postRequest);
    String json = objectMapper.writeValueAsString(post);
    return ResponseEntity.ok(json);
  }

  @DeleteMapping("/posts/delete/{id}")
  public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
    Boolean isDeleted = postService.deletePostById(id);
    if (isDeleted) {
      return ResponseEntity.ok("Success");
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @Autowired
  public PostController(PostService postService, ObjectMapper objectMapper) {
    this.postService = postService;
    this.objectMapper = objectMapper;
  }
}
