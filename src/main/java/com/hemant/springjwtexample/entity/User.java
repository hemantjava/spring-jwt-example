package com.hemant.springjwtexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @GeneratedValue
  @Id
  private Long id;
  private String userName;
  private String passWord;


}
