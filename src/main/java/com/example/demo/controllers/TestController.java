package com.example.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
  private PasswordEncoder passwordEncoder;

  TestController(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("hello")
  public String helloWorldFunction() {
    return "Hello World";
  }

  @GetMapping("/hash")
  public Integer getMyName() {
    return passwordEncoder.hashCode();
  }
}
