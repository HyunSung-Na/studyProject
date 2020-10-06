package com.infinite.study.controller.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class StudyRequest {

    @ApiModelProperty(value = "스터디명", required = true)
    private String title;

    @ApiModelProperty(value = "짧은 소개", required = true)
    private String shortDescription;

    @ApiModelProperty(value = "내용", required = true)
    private String fullDescription;

    @ApiModelProperty(value = "지역", required = true)
    private String zones;

    protected StudyRequest() {}

    public Study newStudy(Id<User, Long> userId, Writer writer) {
        return new Study(userId, title, shortDescription, fullDescription, zones, writer);
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getZones() {
        return zones;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("shortDescription", shortDescription)
                .append("fullDescription", fullDescription)
                .append("zones", zones)
                .toString();
    }
}
