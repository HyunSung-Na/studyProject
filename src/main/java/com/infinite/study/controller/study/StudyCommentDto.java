package com.infinite.study.controller.study;

import com.infinite.study.model.study.StudyComment;
import com.infinite.study.util.Writer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class StudyCommentDto {

    private Long seq;

    private String comments;

    private Writer writer;

    private LocalDateTime create_at;

    public StudyCommentDto(StudyComment source) {
        copyProperties(source, this);

        this.writer = source.getWriter().orElse(null);
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
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
                .append("comments", comments)
                .append("writer", writer)
                .append("create_at", create_at)
                .toString();
    }
}
