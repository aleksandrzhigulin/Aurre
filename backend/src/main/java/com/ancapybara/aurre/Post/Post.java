package com.ancapybara.aurre.Post;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    private String author;

    @ManyToMany
    private List<PostComponent> components;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Post() {
        this.title = null;
        this.author = null;
    }
    public Post(String title) {
        this.title = title;
        this.author = "unknown";
    }

    public Post(String title, String author) {
        this.title = title;
        this.author = author;
        this.components = new ArrayList<>();
    }

    public Post(String title, String author, List<PostComponent> components) {
        this.title = title;
        this.author = author;
        this.components = components;
    }

    public List<PostComponent> getComponents() {
        return components;
    }

    public void setComponents(List<PostComponent> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle()) &&  Objects.equals(getAuthor(), post.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), getComponents());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
