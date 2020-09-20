package com.infinite.study.service;

import com.infinite.study.error.NotFoundException;
import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.study.StudyComment;
import com.infinite.study.model.user.User;
import com.infinite.study.repository.study.StudyCommentRepository;
import com.infinite.study.repository.study.StudyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.emptyList;

@Service
public class StudyCommentService {

    private final StudyRepository studyRepository;

    private final StudyCommentRepository studyCommentRepository;

    public StudyCommentService(StudyRepository studyRepository, StudyCommentRepository studyCommentRepository) {
        this.studyRepository = studyRepository;
        this.studyCommentRepository = studyCommentRepository;
    }

    @Transactional
    public StudyComment write(Id<Study, Long> studyId, Id<User, Long> studyWriterId, StudyComment studyComment) {
        checkArgument(studyComment.getStudy_seq().equals(studyId), "comment.studyId must equals studyId");
        checkNotNull(studyComment, "studyComment must be provided.");

        return findStudy(studyId, studyWriterId)
                .map(study -> {
                    studyRepository.update(study);
                    return insert(studyComment);
                })
                .orElseThrow(() -> new NotFoundException(Study.class, studyId));
    }

    @Transactional(readOnly = true)
    public List<StudyComment> findAll(Id<Study, Long> studyId, Id<User, Long> studyWriterId) {
        return findStudy(studyId, studyWriterId)
                .map(study -> studyCommentRepository.findAll(studyId))
                .orElse(emptyList());
    }

    private Optional<Study> findStudy(Id<Study, Long> studyId, Id<User, Long> studyWriterId) {
        checkNotNull(studyId, "studyId must be provided.");
        checkNotNull(studyWriterId, "studyWriterId must be provided.");

        return studyRepository.findById(studyId, studyWriterId);
    }

    private StudyComment insert(StudyComment studyComment) {
        return studyCommentRepository.insert(studyComment);
    }

    private void update(StudyComment studyComment) {
        studyCommentRepository.update(studyComment);
    }
}
