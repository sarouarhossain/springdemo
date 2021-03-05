package com.example.demo.services;

public interface EncryptionService {
  public String encrypt(String data);

  public String decrypt(String encryptedData);
}
