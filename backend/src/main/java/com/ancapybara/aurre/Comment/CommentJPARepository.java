package com.ancapybara.aurre.Comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJPARepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByPostId(Long postId);
}
