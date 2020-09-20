package com.infinite.study.model.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
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
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class StudyComment {

  private final Long seq;

  private final Id<User, Long> user_seq;

  private final Id<Study, Long> study_seq;

  private String contents;

  private final Writer writer;

  private final LocalDateTime create_at;

  public StudyComment(Id<User, Long> user_seq, Id<Study, Long> study_seq, Writer writer, String contents) {
    this(null, user_seq, study_seq, contents, writer, null);
  }

  public StudyComment(Long seq, Id<User, Long> user_seq, Id<Study, Long> study_seq, String contents, Writer writer, LocalDateTime create_at) {
    checkNotNull(user_seq, "user_seq must be provided.");
    checkNotNull(study_seq, "study_seq must be provided.");
    checkArgument(isNotEmpty(contents), "contents must be provided.");
    checkArgument(
      contents.length() >= 4 && contents.length() <= 500,
      "comment contents length must be between 4 and 500 characters."
    );

    this.seq = seq;
    this.user_seq = user_seq;
    this.study_seq = study_seq;
    this.contents = contents;
    this.writer = writer;
    this.create_at = defaultIfNull(create_at, now());
  }

    public void modify(String contents) {
    checkArgument(isNotEmpty(contents), "contents must be provided.");
    checkArgument(
      contents.length() >= 4 && contents.length() <= 500,
      "study contents length must be between 4 and 500 characters."
    );

    this.contents = contents;
  }

  public Long getSeq() {
    return seq;
  }

  public Id<User, Long> getUser_seq() {
    return user_seq;
  }

  public Id<Study, Long> getStudy_seq() {
    return study_seq;
  }

  public String getContents() {
    return contents;
  }

  public Writer getWriter() {
    return writer;
  }

  public LocalDateTime getCreate_at() {
    return create_at;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudyComment comment = (StudyComment) o;
    return Objects.equals(seq, comment.seq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seq);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("seq", seq)
      .append("user_seq", user_seq)
      .append("study_seq", study_seq)
      .append("contents", contents)
      .append("writer", writer)
      .append("create_at", create_at)
      .toString();
  }

  static public class Builder {
    private Long seq;
    private Id<User, Long> userId;
    private Id<Study, Long> study_seq;
    private String contents;
    private Writer writer;
    private LocalDateTime create_at;

    public Builder() {}

    public Builder(StudyComment comment) {
      this.seq = comment.seq;
      this.userId = comment.user_seq;
      this.study_seq = comment.study_seq;
      this.contents = comment.contents;
      this.writer = comment.writer;
      this.create_at = comment.create_at;
    }

    public Builder seq(Long seq) {
      this.seq = seq;
      return this;
    }

    public Builder user_seq(Id<User, Long> user_seq) {
      this.userId = user_seq;
      return this;
    }

    public Builder study_seq(Id<Study, Long> study_seq) {
      this.study_seq = study_seq;
      return this;
    }

    public Builder contents(String contents) {
      this.contents = contents;
      return this;
    }

    public Builder writer(Writer writer) {
      this.writer = writer;
      return this;
    }

    public Builder create_at(LocalDateTime create_at) {
      this.create_at = create_at;
      return this;
    }

    public StudyComment build() {
      return new StudyComment(seq, userId, study_seq, contents, writer, create_at);
    }
  }

}