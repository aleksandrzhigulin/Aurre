package com.ancapybara.aurre.domain.dto.post;

import com.ancapybara.aurre.domain.entity.PostComponent;

import java.util.List;

public class PostRequestDTO {

  private String title;
  private String content;

  private String author;

  private String preview;

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

  public String getPreview() {
    return preview;
  }

  public void setPreview(String preview) {
    this.preview = preview;
  }

  public PostRequestDTO(String title, String author, List<PostComponent> components, String preview) {
    this.title = title;
    this.author = author;
    this.components = components;
    this.preview = preview;
  }
}
