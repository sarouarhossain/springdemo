package com.test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class WelcomeTest implements RequestHandler<Object, Object> {
  @Override
  public Object handleRequest(Object input, Context context) {
    System.out.println("Hello world Roman.............");
    return input;
  }
}
