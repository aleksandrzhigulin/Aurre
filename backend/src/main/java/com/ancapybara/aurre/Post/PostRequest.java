package com.ancapybara.aurre.Post;

import java.util.List;

public class PostRequest {
    private String title;
    private String content;

    private String author;

    private List<PostComponent> components;

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

    public List<PostComponent> getComponents() {
        return components;
    }

    public void setComponents(List<PostComponent> components) {
        this.components = components;
    }

    public PostRequest(String title, String author, List<PostComponent> components) {
        this.title = title;
        this.author = author;
        this.components = components;
    }
}
