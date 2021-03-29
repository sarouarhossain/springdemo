package com.testmodule;

import com.test.pkgone.HelloWorld;
import com.test.pkgone.HelloWorldImpl;
import com.test2.ytone.HelloYoutube;
import com.test2.ytone.HelloYoutubeImpl;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // write your code here
    HelloWorld helloWorld = new HelloWorldImpl();

    helloWorld.print();

    HelloYoutube helloYoutube = new HelloYoutubeImpl();
    helloYoutube.print();
  }
}
