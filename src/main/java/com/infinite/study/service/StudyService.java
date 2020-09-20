package com.infinite.study.service;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.user.User;
import com.infinite.study.repository.study.StudyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Transactional
    public Study write(Study study) {
        return insert(study);
    }

    @Transactional
    public Study modify(Study study) {
        update(study);
        return study;
    }


    @Transactional(readOnly = true)
    public Optional<Study> findById(Id<Study, Long> studyId, Id<User, Long> writerId) {
        checkNotNull(writerId, "writerId must be provided.");
        checkNotNull(studyId, "postId must be provided.");

        return studyRepository.findById(studyId, writerId);
    }

    @Transactional(readOnly = true)
    public List<Study> findAll(Id<User, Long> writerId, long offset, int limit) {
        checkNotNull(writerId, "writerId must be provided.");
        if (offset < 0)
            offset = 0;
        if (limit < 1 || limit > 5)
            limit = 5;

        return studyRepository.findAll(writerId, offset, limit);
    }

    private Study insert(Study study) {
        return studyRepository.insert(study);
    }

    private void update(Study study) {
        studyRepository.update(study);
    }
}
