package com.infinite.study.controller.study;

import com.infinite.study.model.study.StudyComment;
import com.infinite.study.util.Writer;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class StudyCommentDto {

    @ApiModelProperty(value = "PK", required = true)
    private Long seq;

    @ApiModelProperty(value = "내용", required = true)
    private String comments;

    @ApiModelProperty(value = "작성자")
    private Writer writer;

    @ApiModelProperty(value = "작성일시", required = true)
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
