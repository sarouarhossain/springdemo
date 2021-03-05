package com.example.demo.controllers;

import com.example.demo.services.EncryptionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mock")
public class EncryptionController {
  EncryptionService encryptionService;

  EncryptionController(EncryptionService encryptionService) {
    this.encryptionService = encryptionService;
  }

  @PostMapping("/{data}")
  public String postEncrypt(@PathVariable String data) {
    return encryptionService.encrypt(data);
  }

  // http://localhost:9090/api/v1/mock/YSHFH2gcbTghka9YCwPc8g==
  // http://localhost:9090/api/v1/mock/Be4jcg1s2UIM1x97NsrAiw==
  // http://localhost:9090/api/v1/mock/HoxrBKrehQRLx6d78AofvQ==
  // http://localhost:9090/api/v1/mock/mM1JUCIcB7V5EQdLO0qM5A==
  // http://localhost:9090/api/v1/mock/tKSsrztNKhWTHdYIxPK8fQ==
  // http://localhost:9090/api/v1/mock/kZnHt9fPwJuvoVWanA0cFg==
  // http://localhost:9090/api/v1/mock/jsbVK8r0HOLqn8PRDJb0kQ==
  // http://localhost:9090/api/v1/mock/wOeIdmthQWc3Brnfhp+5ng==
  // http://localhost:9090/api/v1/mock/sp4CjEkCPWzDjdm9bwxDug==
  // http://localhost:9090/api/v1/mock/aa2tvZRyGgjL7yTIOJ0E4g==
  @GetMapping("/{data}")
  public String getDecrypt(@PathVariable String data) {
    return encryptionService.decrypt(data);
  }
}
