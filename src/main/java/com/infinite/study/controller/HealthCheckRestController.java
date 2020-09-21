package com.infinite.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.infinite.study.controller.ApiResult.OK;

@RestController
@RequestMapping("api/_hcheck")
public class HealthCheckRestController {

  @GetMapping
  public ApiResult<Long> healthCheck() {
    return OK(System.currentTimeMillis());
  }

}