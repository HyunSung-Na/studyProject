package com.infinite.study.controller.study;

import com.infinite.study.model.study.Study;
import com.infinite.study.util.Writer;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class StudyDto {

    @ApiModelProperty(value = "PK", required = true)
    private Long seq;

    @ApiModelProperty(value = "제목", required = true)
    private String title;

    @ApiModelProperty(value = "짧은 소개", required = true)
    private String shortDescription;

    @ApiModelProperty(value = "내용", required = true)
    private String fullDescription;

    @ApiModelProperty(value = "지역", required = true)
    private String zones;

    @ApiModelProperty(value = "작성자")
    private Writer writer;

    @ApiModelProperty(value = "생성일자", required = true)
    private LocalDateTime publishDateTime;

    public StudyDto(Study source) {
        copyProperties(source, this);

        this.writer = source.getWriter().orElse(null);
    }


    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(LocalDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("seq", seq)
                .append("title", title)
                .append("shortDescription", shortDescription)
                .append("fullDescription", fullDescription)
                .append("zones", zones)
                .append("writer", writer)
                .append("publishDateTime", publishDateTime)
                .toString();
    }
}
