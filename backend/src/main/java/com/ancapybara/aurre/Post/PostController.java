package com.ancapybara.aurre.Post;

import com.ancapybara.aurre.Security.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class PostController {
    @Autowired
    private JWTManager jwtManager;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/get/all")
    public ResponseEntity<String> getAllPosts() {
        Iterable<Post> posts = postRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (var post : posts) {
            result.append(post.toString()).append("\n");
        }

        return ResponseEntity.ok(result.toString());
    }

    @PostMapping("/posts/create")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        String title = postRequest.getTitle();
        String content = postRequest.getContent();
        String author = postRequest.getAuthor();
        postRepository.save(new Post(title, content, author));

        return ResponseEntity.ok("Success");
    }
}
