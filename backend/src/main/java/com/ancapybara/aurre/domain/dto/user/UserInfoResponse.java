package com.ancapybara.aurre.domain.dto.user;

public class UserInfoResponse {

  private Long id;
  private String username;
  private String avatarFilename;

  public UserInfoResponse() {

  }

  public UserInfoResponse(Long id, String username, String avatarFilename) {
    this.id = id;
    this.username = username;
    this.avatarFilename = avatarFilename;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatarFilename() {
    return avatarFilename;
  }

  public void setAvatarFilename(String avatarFilename) {
    this.avatarFilename = avatarFilename;
  }
}
