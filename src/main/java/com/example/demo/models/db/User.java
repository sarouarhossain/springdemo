package com.example.demo.models.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {
  @GeneratedValue private Long id;
  private String username;
  private String email;
  private String password;
  private Integer age;
}
