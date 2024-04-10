package com.ancapybara.aurre.domain.dto.comment;

public class CommentRequestDTO {
    private Long postId;
    private String text;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentRequestDTO() {}

    public CommentRequestDTO(Long postId, String text) {
        this.postId = postId;
        this.text = text;
    }
}
