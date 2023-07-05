package com.ancapybara.aurre.Post;

public class PostRequest {
    private String title;
    private String content;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
