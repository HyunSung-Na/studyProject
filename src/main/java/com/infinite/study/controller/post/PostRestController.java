package com.infinite.study.controller.post;


import com.infinite.study.configure.support.Pageable;
import com.infinite.study.controller.ApiResult;
import com.infinite.study.error.NotFoundException;
import com.infinite.study.model.Id;
import com.infinite.study.model.posts.Post;
import com.infinite.study.model.user.User;
import com.infinite.study.security.JwtAuthentication;
import com.infinite.study.service.CommentService;
import com.infinite.study.service.PostService;
import com.infinite.study.util.Writer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.infinite.study.controller.ApiResult.OK;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api")
public class PostRestController {

  private final PostService postService;

  private final CommentService commentService;

  public PostRestController(PostService postService, CommentService commentService) {
    this.postService = postService;
    this.commentService = commentService;
  }

  @PostMapping(path = "post")
  public ApiResult<PostDto> posting(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @RequestBody PostingRequest request
  ) {
    return OK(
      new PostDto(
        postService.write(
          request.newPost(authentication.id, new Writer(authentication.email, authentication.name))
        )
      )
    );
  }

  @GetMapping(path = "user/{userId}/post/list")
  public ApiResult<List<PostDto>> posts(
    @AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long userId,
    Pageable pageable) {
    return OK(
      postService.findAll(Id.of(User.class, userId), authentication.id, pageable.offset(), pageable.limit()).stream()
        .map(PostDto::new)
        .collect(toList())
    );
  }

  @PatchMapping(path = "user/{userId}/post/{postId}/like")
  public ApiResult<PostDto> like(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @PathVariable Long userId,
    @PathVariable Long postId
  ) {
    return OK(
      postService.like(Id.of(Post.class, postId), Id.of(User.class, userId), authentication.id)
        .map(PostDto::new)
        .orElseThrow(() -> new NotFoundException(Post.class, Id.of(Post.class, postId), Id.of(User.class, userId)))
    );
  }

  @PostMapping(path = "user/{userId}/post/{postId}/comment")
  public ApiResult<CommentDto> comment(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @PathVariable Long userId,
    @PathVariable Long postId,
    @RequestBody CommentRequest request
  ) {
    return OK(
      new CommentDto(
        commentService.write(
          Id.of(Post.class, postId),
          Id.of(User.class, userId),
          authentication.id,
          request.newComment(
            authentication.id, Id.of(Post.class, postId),
            new Writer(authentication.email, authentication.name)
          )
        )
      )
    );
  }

  @GetMapping(path = "user/{userId}/post/{postId}/comment/list")
  public ApiResult<List<CommentDto>> comments(
    @AuthenticationPrincipal JwtAuthentication authentication,
    @PathVariable Long userId,
    @PathVariable Long postId
  ) {
    return OK(
      commentService.findAll(Id.of(Post.class, postId), Id.of(User.class, userId), authentication.id).stream()
        .map(CommentDto::new)
        .collect(toList())
    );
  }

}