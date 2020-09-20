package com.infinite.study.repository.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.user.User;

import java.util.List;
import java.util.Optional;

public interface StudyRepository {

    Study insert(Study study);

    void update(Study study);

    Optional<Study> findById(Id<Study, Long> studyId, Id<User, Long> writerId);

    List<Study> findAll(Id<User, Long> writerId, long offset, int limit);

}
