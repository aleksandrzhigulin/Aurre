package com.ancapybara.aurre.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, Long> {

  List<Post> findAllByAuthor(String author);
}
