package com.ancapybara.aurre.Post;

import jakarta.persistence.*;

@Entity
public class PostComponent {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String type;
  @Column(columnDefinition = "TEXT")
  private String body;

  public PostComponent() {

  }

  public PostComponent(String type, String body) {
    this.type = type;
    this.body = body;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
