package com.infinite.study.repository.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Comment;
import com.infinite.study.model.posts.Post;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment insert(Comment comment);

    void update(Comment comment);

    Optional<Comment> findById(Id<Comment, Long> commentId);

    List<Comment> findAll(Id<Post, Long> postId);
}
