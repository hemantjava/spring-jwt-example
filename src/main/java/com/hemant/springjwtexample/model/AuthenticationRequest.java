package com.hemant.springjwtexample.model;

import lombok.Value;

@Value
public class AuthenticationRequest {

  private String username;
  private String password;

}
