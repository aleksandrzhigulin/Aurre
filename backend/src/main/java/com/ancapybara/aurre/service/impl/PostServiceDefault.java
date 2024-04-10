package com.ancapybara.aurre.service.impl;

import com.ancapybara.aurre.domain.dto.post.PostRequestDTO;
import com.ancapybara.aurre.repository.PostComponentRepository;
import com.ancapybara.aurre.repository.PostCrudRepository;
import com.ancapybara.aurre.repository.PostJPARepository;
import com.ancapybara.aurre.service.PostService;
import com.ancapybara.aurre.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceDefault implements PostService {
    private final PostCrudRepository postCrudRepository;

    private final PostJPARepository postJPARepository;

    private final PostComponentRepository componentRep;

    @Override
    public Iterable<Post> getAllPosts() {
        return postCrudRepository.findAll();
    }

    @Override
    public Iterable<Post> getAllPostsByAuthor(String author) {
        return postJPARepository.findAllByAuthor(author);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postCrudRepository.findById(id);
    }

    @Override
    public Post createPost(PostRequestDTO postRequestDTO) {
        Post post = new Post(postRequestDTO.getTitle(),
                postRequestDTO.getAuthor(),
                postRequestDTO.getComponents(),
                postRequestDTO.getPreview());
        componentRep.saveAll(postRequestDTO.getComponents());
        return postCrudRepository.save(post);
    }

    @Override
    public Boolean deletePostById(Long id) {
        postCrudRepository.deleteById(id);
        return postCrudRepository.findById(id).isEmpty();
    }

    @Autowired
    public PostServiceDefault(PostCrudRepository postCrudRepository, PostJPARepository postJPARepository, PostComponentRepository componentRep) {
        this.postCrudRepository = postCrudRepository;
        this.postJPARepository = postJPARepository;
        this.componentRep = componentRep;
    }
}
