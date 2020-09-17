package com.infinite.study.repository.study;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.study.Study;
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

@Repository
public class JdbcStudyRepository implements StudyRepository{

    JdbcTemplate jdbcTemplate;


    @Override
    public Study insert(Study study) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO study(seq,user_seq,title,shortDescription,fullDescription,zones,publishDateTime) VALUES (null,?,?,?,?,?,?)", new String[]{"seq"});
            ps.setLong(1, study.getUser_seq().value());
            ps.setString(2, study.getTitle());
            ps.setString(3, study.getShortDescription());
            ps.setString(4, study.getFullDescription());
            ps.setString(5, study.getZones());
            ps.setTimestamp(6, timestampOf(study.getPublishDateTime()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return new Study.Builder(study)
                .seq(generatedSeq)
                .build();
    }

    @Override
    public void update(Study study) {

    }

    @Override
    public Optional<Study> findById(Id<Study, Long> studyId, Id<User, Long> writerId, Id<User, Long> userId) {
        return Optional.empty();
    }

    @Override
    public List<Study> findAll(Id<User, Long> writerId, Id<User, Long> userId, long offset, int limit) {
        return null;
    }

    static RowMapper<Study> mapper = (rs, rowNum) -> new Study.Builder()
            .seq(rs.getLong("seq"))
            .user_seq(Id.of(User.class, rs.getLong("user_seq")))
            .title(rs.getString("title"))
            .shortDescription(rs.getString("shortDescription"))
            .fullDescription(rs.getString("fullDescription"))
            .zones(rs.getString("zones"))
            .writer(new Writer(new Email(rs.getString("email")), rs.getString("name")))
            .publishDateTime(dateTimeOf(rs.getTimestamp("publishDateTime")))
            .build();
}
