package com.ancapybara.aurre.controller;

import com.ancapybara.aurre.domain.dto.comment.CommentDTO;
import com.ancapybara.aurre.domain.dto.comment.CommentRequestDTO;
import com.ancapybara.aurre.domain.mapper.CommentMapper;
import com.ancapybara.aurre.service.CommentService;

import java.security.Principal;
import java.util.List;

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
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(CommentService commentService,
                             CommentMapper mapper) {
        this.commentService = commentService;
        this.commentMapper = mapper;
    }


    @PostMapping("/comments/create")
    public ResponseEntity<?> createComment(Principal principal,
                                           @RequestBody CommentRequestDTO request) {
        CommentDTO dto = commentService.createComment(principal, request);
        return new ResponseEntity<>(commentMapper.toResponse(dto), HttpStatus.OK);
    }

    @GetMapping("/comments/get/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        try {
            CommentDTO dto = commentService.getCommentById(id);
            return new ResponseEntity<>(commentMapper.toResponse(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/comments/post/{id}")
    public ResponseEntity<?> getAllCommentsByPostId(@PathVariable("id") Long postId) {
        List<CommentDTO> DTOs = (List<CommentDTO>) commentService.getAllCommentsById(postId);
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }

    @DeleteMapping("/comments/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        try {
            commentService.deleteCommentById(id);
            return ResponseEntity.ok("Success");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
