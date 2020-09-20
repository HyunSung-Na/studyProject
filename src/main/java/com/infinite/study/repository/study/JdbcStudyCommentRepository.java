package com.infinite.study.repository.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.study.StudyComment;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import com.infinite.study.util.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static com.infinite.study.util.DateTimeUtils.dateTimeOf;
import static com.infinite.study.util.DateTimeUtils.timestampOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcStudyCommentRepository implements StudyCommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStudyCommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public StudyComment insert(StudyComment studyComment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO study_comment(seq,user_seq,study_seq,comments,create_at) VALUES (null,?,?,?,?)", new String[]{"seq"});
            ps.setLong(1, studyComment.getUser_seq().value());
            ps.setLong(2, studyComment.getStudy_seq().value());
            ps.setString(3, studyComment.getContents());
            ps.setTimestamp(4, timestampOf(studyComment.getCreate_at()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return new StudyComment.Builder(studyComment)
                .seq(generatedSeq)
                .build();
    }

    @Override
    public void update(StudyComment studyComment) {
        jdbcTemplate.update(
                "UPDATE study_comment SET comments=? WHERE seq=?",
                studyComment.getContents(),
                studyComment.getSeq()
        );
    }

    @Override
    public Optional<StudyComment> findById(Id<StudyComment, Long> studyCommentLongId) {
        List<StudyComment> results = jdbcTemplate.query(
                "SELECT sc.*,u.email,u.name FROM study_comment sc JOIN users u ON sc.user_seq=u.seq WHERE sc.seq=?",
                new Object[]{studyCommentLongId.value()},
                mapper
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public List<StudyComment> findAll(Id<Study, Long> studyId) {
        return jdbcTemplate.query(
                "SELECT sc.*,u.email,u.name FROM study_comment sc JOIN users u ON sc.user_seq=u.seq WHERE sc.study_seq=? ORDER BY sc.seq DESC",
                new Object[]{studyId.value()},
                mapper
        );
    }

    static RowMapper<StudyComment> mapper = (rs, rowNum) -> new StudyComment.Builder()
            .seq(rs.getLong("seq"))
            .user_seq(Id.of(User.class, rs.getLong("user_seq")))
            .study_seq(Id.of(Study.class, rs.getLong("study_seq")))
            .contents(rs.getString("comments"))
            .writer(new Writer(new Email(rs.getString("email")), rs.getString("name")))
            .create_at(dateTimeOf(rs.getTimestamp("create_at")))
            .build();

}
