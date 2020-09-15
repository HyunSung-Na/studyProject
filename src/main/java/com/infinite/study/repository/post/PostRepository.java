package com.infinite.study.repository.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post insert(Post post);

    void update(Post post);

    Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> writerId, Id<User, Long> userId);

    List<Post> findAll(Id<User, Long> writerId, Id<User, Long> userId, long offset, int limit);

}
