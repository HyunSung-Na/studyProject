package com.infinite.study.study;

import com.infinite.study.error.NotFoundException;
import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.study.StudyComment;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import com.infinite.study.service.StudyCommentService;
import com.infinite.study.service.StudyService;
import com.infinite.study.util.Writer;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyCommentServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyCommentService studyCommentService;

    private Id<Study, Long> studyId;

    private Id<User, Long> studyWriterId;

    private Id<User, Long> userId;

    @BeforeAll
    void setUp() {
        studyId = Id.of(Study.class, 1L);
        studyWriterId = Id.of(User.class, 1L);
        userId = Id.of(User.class, 2L);
    }

    @Test
    @Order(1)
    void 코멘트를_작성한다() {
        String contents = randomAlphabetic(40);
        StudyComment comment = studyCommentService.write(
                studyId,
                studyWriterId,
                new StudyComment(userId, studyId, new Writer(new Email("test00@gmail.com"), "test00"), contents)
        );
        Study study = studyService.findById(studyId, studyWriterId).orElseThrow(() -> new NotFoundException(Study.class, studyId));
        assertThat(study, is(notNullValue()));
        assertThat(comment, is(notNullValue()));
        assertThat(comment.getSeq(), is(notNullValue()));
        assertThat(comment.getContents(), is(contents));
        logger.info("Written StudyComment: {}", comment);
    }

    @Test
    @Order(2)
    void 댓글_목록을_조회한다() {
        List<StudyComment> comments = studyCommentService.findAll(studyId, studyWriterId);
        assertThat(comments, is(notNullValue()));
        assertThat(comments.size(), is(2));
    }
}
