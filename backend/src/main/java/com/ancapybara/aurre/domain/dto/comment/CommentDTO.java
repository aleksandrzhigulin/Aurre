package com.ancapybara.aurre.domain.dto.comment;

import com.ancapybara.aurre.domain.entity.User;

public class CommentDTO {
    private Long postId;
    private User author;
    private String text;

    public CommentDTO() {}

    public CommentDTO(Long postId, User author, String text) {
        this.postId = postId;
        this.author = author;
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
