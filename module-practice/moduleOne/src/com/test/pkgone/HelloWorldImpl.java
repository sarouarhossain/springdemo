package com.test.pkgone;

import com.test.pkgone.HelloWorld;

public class HelloWorldImpl implements HelloWorld {
  public void print() {
    printHello();
  }

  private void printHello() {
    System.out.println("HELLO WORLD!!!!!");
  }
}
