package com.infinite.study.repository.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Comment;
import com.infinite.study.model.posts.Post;
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
public class JdbcCommentRepository implements CommentRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcCommentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Comment insert(Comment comment) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(conn -> {
      PreparedStatement ps = conn.prepareStatement("INSERT INTO post_comments(seq,user_seq,post_seq,contents,create_at) VALUES (null,?,?,?,?)", new String[]{"seq"});
      ps.setLong(1, comment.getUserId().value());
      ps.setLong(2, comment.getPostId().value());
      ps.setString(3, comment.getContents());
      ps.setTimestamp(4, timestampOf(comment.getCreateAt()));
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    long generatedSeq = key != null ? key.longValue() : -1;
    return new Comment.Builder(comment)
      .seq(generatedSeq)
      .build();
  }

  @Override
  public void update(Comment comment) {
    jdbcTemplate.update(
      "UPDATE post_comments SET contents=? WHERE seq=?",
      comment.getContents(),
      comment.getSeq()
    );
  }

  @Override
  public Optional<Comment> findById(Id<Comment, Long> commentId) {
    List<Comment> results = jdbcTemplate.query(
      "SELECT c.*,u.email,u.name FROM post_comments c JOIN users u ON c.user_seq=u.seq WHERE c.seq=?",
      new Object[]{commentId.value()},
      mapper
    );
    return ofNullable(results.isEmpty() ? null : results.get(0));
  }

  @Override
  public List<Comment> findAll(Id<Post, Long> postId) {
    return jdbcTemplate.query(
      "SELECT c.*,u.email,u.name FROM post_comments c JOIN users u ON c.user_seq=u.seq WHERE c.post_seq=? ORDER BY c.seq DESC",
      new Object[]{postId.value()},
      mapper
    );
  }

  static RowMapper<Comment> mapper = (rs, rowNum) -> new Comment.Builder()
    .seq(rs.getLong("seq"))
    .userId(Id.of(User.class, rs.getLong("user_seq")))
    .postId(Id.of(Post.class, rs.getLong("post_seq")))
    .contents(rs.getString("contents"))
    .writer(new Writer(new Email(rs.getString("email")), rs.getString("name")))
    .createAt(dateTimeOf(rs.getTimestamp("create_at")))
    .build();

}