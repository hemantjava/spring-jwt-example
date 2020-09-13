package com.hemant.springjwtexample.model;

import lombok.Value;

@Value
public class AuthenticationResponse {

  private String jwtToken;

}
