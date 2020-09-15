package com.infinite.study.repository.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPostRepository implements PostRepository{



    @Override
    public Post insert(Post post) {
        return null;
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> writerId, Id<User, Long> userId) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAll(Id<User, Long> writerId, Id<User, Long> userId, long offset, int limit) {
        return null;
    }
}
