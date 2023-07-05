package com.ancapybara.aurre.Post;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Content")
    private String content;

    private String author;

    public Long getId() {
        return id;
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
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Post() {
        this.title = null;
        this.author = null;
        this.content = null;
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.author = "unknown";
    }

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getContent(), post.getContent()) && Objects.equals(getAuthor(), post.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getAuthor());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
