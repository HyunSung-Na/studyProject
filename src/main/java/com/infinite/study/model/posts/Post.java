package com.infinite.study.model.posts;

import com.infinite.study.model.Id;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.time.chrono.ChronoLocalDateTime.timeLineOrder;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Post {

  private final Long seq;

  private final Id<User, Long> userId;

  private String contents;

  private int likes;

  private boolean likesOfMe;

  private int comments;

  private final Writer writer;

  private final LocalDateTime createAt;

  private String title;

  public Post(Id<User, Long> userId, Writer writer, String contents, String title) {
    this(null, userId, contents, 0, false, 0, writer, null, title);
  }

  public Post(Long seq, Id<User, Long> userId, String contents, int likes, boolean likesOfMe, int comments, Writer writer, LocalDateTime createAt, String title) {
    checkNotNull(userId, "userId must be provided.");
    checkArgument(isNotEmpty(contents), "contents must be provided.");
    checkArgument(
      contents.length() >= 4 && contents.length() <= 500,
      "post contents length must be between 4 and 500 characters."
    );
    checkArgument(isNotEmpty(title), "title must be provided");
    checkArgument(title.length() >= 2 && title.length() <= 100,
            "title length must be between 2 and 100 characters");
    this.seq = seq;
    this.userId = userId;
    this.contents = contents;
    this.likes = likes;
    this.likesOfMe = likesOfMe;
    this.comments = comments;
    this.writer = writer;
    this.createAt = defaultIfNull(createAt, now());
    this.title = title;
  }

  public void modify(String contents, String title) {
    checkArgument(isNotEmpty(contents), "contents must be provided.");
    checkArgument(
      contents.length() >= 4 && contents.length() <= 500,
      "post contents length must be between 4 and 500 characters."
    );
    checkArgument(isNotEmpty(title), "title must be provided");
    checkArgument(title.length() >= 2 && title.length() <= 100,
            "title length must be between 2 and 100 characters");

    this.contents = contents;
    this.title = title;
  }

  public int incrementAndGetLikes() {
    likesOfMe = true;
    return ++likes;
  }

  public int incrementAndGetComments() {
    return ++comments;
  }

  public Long getSeq() {
    return seq;
  }

  public Id<User, Long> getUserId() {
    return userId;
  }

  public String getContents() {
    return contents;
  }

  public int getLikes() {
    return likes;
  }

  public boolean isLikesOfMe() {
    return likesOfMe;
  }

  public int getComments() {
    return comments;
  }

  public Optional<Writer> getWriter() {
    return ofNullable(writer);
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(seq, post.seq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seq);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("userId", userId)
      .append("contents", contents)
      .append("likes", likes)
      .append("likesOfMe", likesOfMe)
      .append("comments", comments)
      .append("writer", writer)
      .append("createAt", createAt)
      .append("title", title)
      .toString();
  }

  static public class Builder {
    private Long seq;
    private Id<User, Long> userId;
    private String contents;
    private int likes;
    private boolean likesOfMe;
    private int comments;
    private Writer writer;
    private LocalDateTime createAt;
    private String title;

    public Builder() {
    }

    public Builder(Post post) {
      this.seq = post.seq;
      this.userId = post.userId;
      this.contents = post.contents;
      this.likes = post.likes;
      this.likesOfMe = post.likesOfMe;
      this.comments = post.comments;
      this.writer = post.writer;
      this.createAt = post.createAt;
      this.title = post.title;
    }

    public Builder seq(Long seq) {
      this.seq = seq;
      return this;
    }

    public Builder userId(Id<User, Long> userId) {
      this.userId = userId;
      return this;
    }

    public Builder contents(String contents) {
      this.contents = contents;
      return this;
    }

    public Builder likes(int likes) {
      this.likes = likes;
      return this;
    }

    public Builder likesOfMe(boolean likesOfMe) {
      this.likesOfMe = likesOfMe;
      return this;
    }

    public Builder comments(int comments) {
      this.comments = comments;
      return this;
    }

    public Builder writer(Writer writer) {
      this.writer = writer;
      return this;
    }

    public Builder createAt(LocalDateTime createAt) {
      this.createAt = createAt;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Post build() {
      return new Post(seq, userId, contents, likes, likesOfMe, comments, writer, createAt, title);
    }
  }

}