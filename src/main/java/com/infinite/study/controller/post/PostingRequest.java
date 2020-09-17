package com.infinite.study.controller.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PostingRequest {

  private String contents;

  private String title;

  protected PostingRequest() {}

  public String getContents() {
    return contents;
  }

  public String getTitle() {
    return title;
  }

  public Post newPost(Id<User, Long> userId, Writer writer) {
    return new Post(userId, writer, contents, title);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("contents", contents)
      .append("title", title)
      .toString();
  }

}