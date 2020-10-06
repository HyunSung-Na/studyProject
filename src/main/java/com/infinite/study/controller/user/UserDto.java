package com.infinite.study.controller.user;

import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class UserDto {

  @ApiModelProperty(value = "PK", required = true)
  private Long seq;

  @ApiModelProperty(value = "사용자명", required = true)
  private String name;

  @ApiModelProperty(value = "이메일", required = true)
  private Email email;

  @ApiModelProperty(value = "로그인 횟수", required = true)
  private int login_count;

  @ApiModelProperty(value = "생성일시", required = true)
  private LocalDateTime create_at;

  @ApiModelProperty(value = "최종로그인일시")
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
      .append("name", name)
      .append("email", email)
      .append("login_count", login_count)
      .append("create_at", create_at)
      .append("last_login_at", last_login_at)
      .toString();
  }

}