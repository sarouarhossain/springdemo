package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.models.UserForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  private List<User> userList = new ArrayList();

  private PasswordEncoder passwordEncoder;

  UserController(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("")
  public User register(@RequestBody UserForm userForm) {
    User user = new User();
    user.setUsername(userForm.getUsername());
    user.setEmail(userForm.getEmail());
    user.setPassword(passwordEncoder.encode(userForm.getPassword()));
    user.setAge(userForm.getAge());
    userList.add(user);

    return user;
  }

  @GetMapping("")
  public List<User> get() {
    return userList;
  }

  @GetMapping("hash")
  public Integer getHash() {
    return passwordEncoder.hashCode();
  }
}
