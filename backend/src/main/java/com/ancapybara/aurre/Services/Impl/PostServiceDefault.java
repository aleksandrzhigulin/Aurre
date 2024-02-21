package com.ancapybara.aurre.Services.Impl;

import com.ancapybara.aurre.Post.*;
import com.ancapybara.aurre.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceDefault implements PostService {
    private PostCrudRepository postCrudRepository;

    private PostJPARepository postJPARepository;

    private PostComponentRepository componentRep;

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
    public Post createPost(PostRequest postRequest) {
        Post post = new Post(postRequest.getTitle(),
                postRequest.getAuthor(),
                postRequest.getComponents(),
                postRequest.getPreview());
        componentRep.saveAll(postRequest.getComponents());
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
