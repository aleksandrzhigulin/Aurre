package com.ancapybara.aurre.service;

import com.ancapybara.aurre.domain.dto.comment.CommentDTO;
import com.ancapybara.aurre.domain.dto.comment.CommentRequestDTO;

import java.security.Principal;

public interface CommentService {
    CommentDTO createComment(Principal principal, CommentRequestDTO dto);

    CommentDTO getCommentById(Long id);

    Iterable<CommentDTO> getAllCommentsById(Long id);

    void deleteCommentById(Long id);
}
