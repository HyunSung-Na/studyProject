package com.infinite.study.controller.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Comment;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommentRequest {

  @ApiModelProperty(value = "내용", required = true)
  private String contents;

  protected CommentRequest() {}

  public String getContents() {
    return contents;
  }

  public Comment newComment(Id<User, Long> userId, Id<Post, Long> postId, Writer writer) {
    return new Comment(userId, postId, writer, contents);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("contents", contents)
      .toString();
  }

}