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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "스터디 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", dataType = "integer", paramType = "query", defaultValue = "0", value = "페이징 offset"),
            @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue = "20", value = "최대 조회 갯수")
    })
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
            @PathVariable @ApiParam(value = "조회 대상자 PK", example = "1") Long userId,
            @PathVariable @ApiParam(value = "대상 스터디 PK", example = "1") Long studyId,
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
            @PathVariable @ApiParam(value = "조회대상자 PK", example = "1") Long userId,
            @PathVariable @ApiParam(value = "대상 포스트 PK", example = "1") Long studyId
    ) {
        return OK(
                studyCommentService.findAll(Id.of(Study.class, studyId), Id.of(User.class, userId)).stream()
                        .map(StudyCommentDto::new)
                        .collect(toList())
        );
    }
}
