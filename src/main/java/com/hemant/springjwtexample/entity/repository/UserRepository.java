package com.hemant.springjwtexample.entity.repository;

import com.hemant.springjwtexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserName(String userName);
}
