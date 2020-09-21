package com.infinite.study.controller.study;

import com.infinite.study.model.study.Study;
import com.infinite.study.util.Writer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class StudyDto {

    private Long seq;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private String zones;

    private Writer writer;

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
