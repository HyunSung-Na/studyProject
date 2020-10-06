package com.infinite.study.controller.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.study.StudyComment;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StudyCommentRequest {

    @ApiModelProperty(value = "내용", required = true)
    private String comments;

    protected StudyCommentRequest() {}

    public String getComments() {
        return comments;
    }

    public StudyComment newComment(Id<User, Long> userId, Id<Study, Long> studyId, Writer writer) {
        return new StudyComment(userId, studyId, writer, comments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("comments", comments)
                .toString();
    }

}
