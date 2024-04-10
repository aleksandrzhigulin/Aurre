package com.ancapybara.aurre.service;

import com.ancapybara.aurre.domain.entity.Post;
import com.ancapybara.aurre.domain.dto.post.PostRequestDTO;

import java.util.Optional;

public interface PostService {
    Iterable<Post> getAllPosts();
    Iterable<Post> getAllPostsByAuthor(String author);

    Optional<Post> getPostById(Long id);

    Post createPost(PostRequestDTO postRequestDTO);

    Boolean deletePostById(Long id);
}
