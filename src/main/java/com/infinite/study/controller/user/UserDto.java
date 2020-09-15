package com.infinite.study.controller.user;

import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class UserDto {

  private Long seq;

  private String nickname;

  private Email email;

  private int login_count;

  private LocalDateTime create_at;

  private LocalDateTime last_login_at;

  public UserDto(User source) {
    copyProperties(source, this);

    this.last_login_at = source.getLast_login_at().orElse(null);
  }

  public Long getSeq() {
    return seq;
  }

  public void setSeq(Long seq) {
    this.seq = seq;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String name) {
    this.nickname = nickname;
  }

  public Email getEmail() {
    return email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public int getLogin_count() {
    return login_count;
  }

  public void setLogin_count(int login_count) {
    this.login_count = login_count;
  }

  public LocalDateTime getLast_login_at() {
    return last_login_at;
  }

  public void setLast_login_at(LocalDateTime last_login_at) {
    this.last_login_at = last_login_at;
  }

  public LocalDateTime getCreate_at() {
    return create_at;
  }

  public void setCreate_at(LocalDateTime create_at) {
    this.create_at = create_at;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("nickname", nickname)
      .append("email", email)
      .append("login_count", login_count)
      .append("create_at", create_at)
      .append("last_login_at", last_login_at)
      .toString();
  }

}