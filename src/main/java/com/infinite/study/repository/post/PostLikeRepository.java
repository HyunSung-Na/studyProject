package com.infinite.study.repository.post;

import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;

public interface PostLikeRepository {

    void like(Id<User, Long> userId, Id<Post, Long> postId);

}
