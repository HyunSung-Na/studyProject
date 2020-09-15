package com.infinite.study.repository;

import com.infinite.study.model.user.Email;
import com.infinite.study.model.Id;
import com.infinite.study.model.user.User;
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
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(seq, nickname, email, password, login_count, create_at, last_login_at) VALUES ( null, ?, ?, ?, ?, ?, ? )", new String[] {"seq"});
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getEmail().getAddress());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getLogin_count());
            ps.setTimestamp(5, timestampOf(user.getCreate_at()));
            ps.setTimestamp(6, timestampOf(user.getLast_login_at().orElse(null)));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedSeq = key != null ? key.longValue() : -1;
        return new User.Builder(user)
                .seq(generatedSeq)
                .build();

    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE users SET nickname=?,password=?,login_count=?,last_login_at=? WHERE seq=?",
                user.getNickname(),
                user.getPassword(),
                user.getLogin_count(),
                user.getLast_login_at().orElse(null),
                user.getSeq()
        );
    }

    @Override
    public Optional<User> findById(Id<User, Long> userId) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE seq=?",
                new Object[]{userId.value()},
                mapper
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE email=?",
                new Object[]{email.getAddress()},
                mapper
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public Optional<User> findByNickname(Id<User, String> nickname) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE nickname=?",
                new Object[]{nickname.value()},
                mapper
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }


    static RowMapper<User> mapper = (rs, rowNum) -> new User.Builder()
            .seq(rs.getLong("seq"))
            .nickname(rs.getString("nickname"))
            .email(new Email(rs.getString("email")))
            .password(rs.getString("password"))
            .login_count(rs.getInt("login_count"))
            .last_login_at(dateTimeOf(rs.getTimestamp("last_login_at")))
            .create_at(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
