package com.ancapybara.aurre.Services;

import com.ancapybara.aurre.Post.Post;
import com.ancapybara.aurre.Post.PostRequest;

import java.util.Optional;

public interface PostService {
    Iterable<Post> getAllPosts();
    Iterable<Post> getAllPostsByAuthor(String author);

    Optional<Post> getPostById(Long id);

    Post createPost(PostRequest postRequest);

    Boolean deletePostById(Long id);
}
