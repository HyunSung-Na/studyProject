package com.infinite.study.controller.user;

import com.infinite.study.controller.ApiResult;
import com.infinite.study.error.NotFoundException;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.Role;
import com.infinite.study.model.user.User;
import com.infinite.study.security.Jwt;
import com.infinite.study.security.JwtAuthentication;
import com.infinite.study.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.infinite.study.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
public class UserRestController {

    private final Jwt jwt;

    private final UserService userService;

    public UserRestController(Jwt jwt, UserService userService) {
        this.jwt = jwt;
        this.userService = userService;
    }

    @PostMapping(path = "user/exists")
    public ApiResult<Boolean> checkEmail(@RequestBody Map<String, String> request) {
        Email email = new Email(request.get("address"));
        return OK(
                userService.findByEmail(email).isPresent()
        );
    }


    @PostMapping(path = "user/join")
    public ApiResult<JoinResult> join(@ModelAttribute JoinRequest joinRequest) {
        User user = userService.join(
                joinRequest.getName(),
                new Email(joinRequest.getPrincipal()),
                joinRequest.getCredentials()
        );

        String apiToken = user.newApiToken(jwt, new String[]{Role.USER.value()});
        return OK(
                new JoinResult(apiToken, user)
        );
    }

    @GetMapping(path = "user/me")
    public ApiResult<UserDto> me(@AuthenticationPrincipal JwtAuthentication authentication) {
        return OK(
                userService.findById(authentication.id)
                        .map(UserDto::new)
                        .orElseThrow(() -> new NotFoundException(User.class, authentication.id))
        );
    }
}
