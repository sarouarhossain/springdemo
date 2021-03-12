package com.example.demo.services;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncryptionServiceImpl implements EncryptionService {
  private static String ALGORITHM = "AES";
  private static String BLOCK = "CBC";
  private static String PADDING = "PKCS5Padding";
  String ivStr = "3Q9Qm7Xl0xn69BIE";
  String keyStr = "s+5DawC7qTAD5wua0XaNtosTRWjnMg/D";
  private SecretKey key;
  private IvParameterSpec iv;

  EncryptionServiceImpl() {
    this.iv = new IvParameterSpec(ivStr.getBytes(StandardCharsets.UTF_8));
    this.key = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), ALGORITHM);
  }

  @Override
  public String encrypt(String data) {
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + BLOCK + "/" + PADDING);
      cipher.init(Cipher.ENCRYPT_MODE, key, iv);
      byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(cipherText);
    } catch (NoSuchPaddingException
        | NoSuchAlgorithmException
        | InvalidAlgorithmParameterException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String decrypt(String encryptedData) {
    try {
      Thread.sleep(1000);
      Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + BLOCK + "/" + PADDING);
      cipher.init(Cipher.DECRYPT_MODE, key, iv);
      byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
      return new String(plainText);
    } catch (NoSuchPaddingException
        | NoSuchAlgorithmException
        | InvalidAlgorithmParameterException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException
        | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // http://localhost:9090/api/v1/mock/+Wyc0Q3IYwr8BRnT9Rieag==
}
