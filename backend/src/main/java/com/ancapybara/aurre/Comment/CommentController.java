package com.ancapybara.aurre.Comment;

import com.ancapybara.aurre.User.MyUserDetailsService;
import com.ancapybara.aurre.User.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class CommentController {

  public record CommentRequest(Long postId, String message) {

  }

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @Autowired
  private CommentJPARepository commentJPARepository;

  @Autowired
  private ObjectMapper objectMapper;

  @PostMapping("/comments/create")
  public ResponseEntity<?> createComment(Principal principal,
      @RequestBody CommentRequest request) throws JsonProcessingException {
    User author = (User) myUserDetailsService.loadUserByUsername(principal.getName());
    Comment comment = new Comment(request.postId, author, request.message);
    commentRepository.save(comment);

    return new ResponseEntity<>(objectMapper.writeValueAsString(comment), HttpStatus.OK);
  }

  @GetMapping("/comments/get/{id}")
  public ResponseEntity<?> getComment(@PathVariable("id") Long id) throws JsonProcessingException {
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(objectMapper.writeValueAsString(comment.get()), HttpStatus.OK);
  }

  @GetMapping("/comments/post/{id}")
  public ResponseEntity<?> getAllCommentsByPostId(@PathVariable("id") Long postId)
      throws JsonProcessingException {
    List<Comment> comments = commentJPARepository.findAllByPostId(postId);
    return new ResponseEntity<>(objectMapper.writeValueAsString(comments), HttpStatus.OK);
  }

  @DeleteMapping("/comments/delete/{id}")
  public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok("Success");
  }

}
