package com.ancapybara.aurre.service.impl;

import com.ancapybara.aurre.domain.dto.comment.CommentDTO;
import com.ancapybara.aurre.domain.dto.comment.CommentRequestDTO;
import com.ancapybara.aurre.domain.entity.CommentDAO;
import com.ancapybara.aurre.domain.entity.User;
import com.ancapybara.aurre.domain.mapper.CommentMapper;
import com.ancapybara.aurre.repository.CommentRepository;
import com.ancapybara.aurre.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CommentServiceDefault implements CommentService {
    private final CommentRepository commentRepository;
    private final UserDetailsService userDetailsService;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceDefault(CommentRepository rep,
                                 UserDetailsService ud,
                                 CommentMapper mapper) {
        this.commentRepository = rep;
        this.userDetailsService = ud;
        this.commentMapper = mapper;
    }

    @Override
    public CommentDTO createComment(Principal principal, CommentRequestDTO dto) {
        User author = (User) userDetailsService.loadUserByUsername(principal.getName());
        CommentDAO dao = new CommentDAO();
        dao.setPostId(dto.getPostId());
        dao.setMessage(dto.getText());
        dao.setAuthor(author);
        commentRepository.save(dao);
        return commentMapper.toDTO(dao);
    }

    @Override
    public CommentDTO getCommentById(Long id) throws RuntimeException {
        Optional<CommentDAO> dao = commentRepository.findById(id);
        if (dao.isEmpty()) throw new RuntimeException("Comment not found");
        return commentMapper.toDTO(dao.get());
    }

    @Override
    public Iterable<CommentDTO> getAllCommentsById(Long id) {
        List<CommentDAO> DAOs = commentRepository.findAllById(id);
        if (DAOs.isEmpty()) throw new RuntimeException("Comments not found");
        return commentMapper.toDTOList(DAOs);
    }

    @Override
    public void deleteCommentById(Long id) {
        Optional<CommentDAO> dao = commentRepository.findById(id);
        if (dao.isEmpty()) throw new RuntimeException("Comment not found");
        commentRepository.delete(dao.get());
    }
}
