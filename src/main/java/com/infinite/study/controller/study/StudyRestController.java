package com.infinite.study.controller.study;

import com.infinite.study.configure.support.Pageable;
import com.infinite.study.controller.ApiResult;
import com.infinite.study.model.Id;
import com.infinite.study.model.study.Study;
import com.infinite.study.model.user.User;
import com.infinite.study.security.JwtAuthentication;
import com.infinite.study.service.StudyCommentService;
import com.infinite.study.service.StudyService;
import com.infinite.study.util.Writer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.infinite.study.controller.ApiResult.OK;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api")
public class StudyRestController {

    private final StudyService studyService;

    private final StudyCommentService studyCommentService;

    public StudyRestController(StudyService studyService, StudyCommentService studyCommentService) {
        this.studyService = studyService;
        this.studyCommentService = studyCommentService;
    }

    @PostMapping(path = "study")
    public ApiResult<StudyDto> newStudy(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @RequestBody StudyRequest request
    ) {
        return OK(
                new StudyDto(
                        studyService.write(
                                request.newStudy(authentication.id, new Writer(authentication.email, authentication.name))
                        )
                )
        );
    }

    @GetMapping(path = "user/{userId}/study/list")
    public ApiResult<List<StudyDto>> studys(
            @AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long userId,
            Pageable pageable) {
        return OK(
                studyService.findAll(Id.of(User.class, userId), pageable.offset(), pageable.limit()).stream()
                        .map(StudyDto::new)
                        .collect(toList())
        );
    }


    @PostMapping(path = "user/{userId}/study/{studyId}/comment")
    public ApiResult<StudyCommentDto> comment(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long userId,
            @PathVariable Long studyId,
            @RequestBody StudyCommentRequest request
    ) {
        return OK(
                new StudyCommentDto(
                        studyCommentService.write(
                                Id.of(Study.class, studyId),
                                authentication.id,
                                request.newComment(
                                        authentication.id, Id.of(Study.class, studyId),
                                        new Writer(authentication.email, authentication.name)
                                )
                        )
                )
        );
    }

    @GetMapping(path = "user/{userId}/study/{studyId}/comment/list")
    public ApiResult<List<StudyCommentDto>> comments(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @PathVariable Long userId,
            @PathVariable Long studyId
    ) {
        return OK(
                studyCommentService.findAll(Id.of(Study.class, studyId), Id.of(User.class, userId)).stream()
                        .map(StudyCommentDto::new)
                        .collect(toList())
        );
    }
}
