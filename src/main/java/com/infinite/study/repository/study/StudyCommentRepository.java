package com.infinite.study.repository.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.study.StudyComment;

import java.util.List;
import java.util.Optional;

public interface StudyCommentRepository {

    StudyComment insert(StudyComment studyComment);

    void update(StudyComment studyComment);

    Optional<StudyComment> findById(Id<StudyComment, Long> studyCommentLongId);

    List<StudyComment> findAll(Id<Study, Long> studyId);
}
