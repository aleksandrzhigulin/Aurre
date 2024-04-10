package com.ancapybara.aurre.domain.dto.comment;

import com.ancapybara.aurre.domain.entity.User;

public class CommentResponseDTO {
    private User author;
    private String text;

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

    public CommentResponseDTO() {
    }

    public CommentResponseDTO(User author, String text) {
        this.author = author;
        this.text = text;
    }
}
