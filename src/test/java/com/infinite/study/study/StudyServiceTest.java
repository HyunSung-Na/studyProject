package com.infinite.study.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import com.infinite.study.service.StudyService;
import com.infinite.study.util.Writer;
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
public class StudyServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudyService studyService;

    private Id<Study, Long> studyId;

    private Id<User, Long> writerId;

    @BeforeAll
    void setUp() {
        studyId = Id.of(Study.class, 1L);
        writerId = Id.of(User.class, 1L);
    }

    @Test
    @Order(1)
    void 스터디를_등록한다() {
        Writer writer = new Writer(new Email("test00@gmail.com"), "test");
        String shortDescription = randomAlphabetic(40);
        String title = randomAlphabetic(10);
        String fullDescription = randomAlphabetic(100);
        String zones = randomAlphabetic(10);
        Study study = studyService.write(new Study(writerId, title, shortDescription, fullDescription, zones, writer));
        assertThat(study, is(notNullValue()));
        assertThat(study.getSeq(), is(notNullValue()));
        assertThat(study.getFullDescription(), is(fullDescription));
        assertThat(study.getTitle(), is(title));
        assertThat(study.getShortDescription(), is(shortDescription));
        assertThat(study.getZones(), is(zones));
        logger.info("Written study: {}", study);
    }

    @Test
    @Order(2)
    void 스터디를_수정한다() {
        Study study = studyService.findById(studyId, writerId).orElse(null);
        assertThat(study, is(notNullValue()));
        String shortDescription = randomAlphabetic(40);
        String title = randomAlphabetic(10);
        String fullDescription = randomAlphabetic(100);
        String zones = randomAlphabetic(10);
        study.modify(title, fullDescription, shortDescription, zones);
        studyService.modify(study);
        assertThat(study, is(notNullValue()));
        assertThat(study.getSeq(), is(notNullValue()));
        assertThat(study.getFullDescription(), is(fullDescription));
        assertThat(study.getTitle(), is(title));
        assertThat(study.getShortDescription(), is(shortDescription));
        assertThat(study.getZones(), is(zones));
        logger.info("Modified study: {}", study);
    }

    @Test
    @Order(3)
    void 스터디_목록을_조회한다() {
        List<Study> studyList = studyService.findAll(writerId,0, 20);
        assertThat(studyList, is(notNullValue()));
        assertThat(studyList.size(), is(5));
    }

}
