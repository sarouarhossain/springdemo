package com.example.demo.controllers;

import com.example.demo.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class TestController {
  private PasswordEncoder passwordEncoder;

  @Autowired CoronaDataService coronaDataService;

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

  @GetMapping("")
  public void test() throws IOException {
    coronaDataService.getData();
    return;
  }
}
