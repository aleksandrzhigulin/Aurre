package com.ancapybara.aurre.repository;

import com.ancapybara.aurre.domain.entity.CommentDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentDAO, Long> {

    List<CommentDAO> findAllById(Long id);
}
