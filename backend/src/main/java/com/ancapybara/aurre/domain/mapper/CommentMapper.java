package com.ancapybara.aurre.domain.mapper;

import com.ancapybara.aurre.domain.dto.comment.CommentDTO;
import com.ancapybara.aurre.domain.dto.comment.CommentResponseDTO;
import com.ancapybara.aurre.domain.entity.CommentDAO;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {
    public CommentDTO toDTO(CommentDAO dao) {
        CommentDTO dto = new CommentDTO();
        dto.setAuthor(dao.getAuthor());
        dto.setText(dao.getMessage());
        dto.setPostId(dao.getPostId());
        return dto;
    }

    public CommentDAO toDAO(CommentDTO dto) {
        CommentDAO dao = new CommentDAO();
        dao.setAuthor(dto.getAuthor());
        dao.setMessage(dto.getText());
        dao.setPostId(dto.getPostId());
        return dao;
    }

    public CommentResponseDTO toResponse(CommentDTO dto) {
        CommentResponseDTO response = new CommentResponseDTO();
        response.setText(dto.getText());
        response.setAuthor(dto.getAuthor());
        return response;
    }

    public List<CommentDTO> toDTOList(List<CommentDAO> daos) {
        List<CommentDTO> DAOs = new ArrayList<>();
        for (CommentDAO dao : daos) {
            DAOs.add(toDTO(dao));
        }
        return DAOs;
    }
}
