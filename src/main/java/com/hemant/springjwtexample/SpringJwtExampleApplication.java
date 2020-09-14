package com.hemant.springjwtexample;

import com.hemant.springjwtexample.entity.User;
import com.hemant.springjwtexample.entity.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j2
@SpringBootApplication
public class SpringJwtExampleApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	private void initUsers() {
		final List<User> userList = Stream
				.of(User.builder().userName("hemant").passWord(bCryptPasswordEncoder.encode("sahu")).build(),
						User.builder().userName("chitresh").passWord(bCryptPasswordEncoder.encode("nirala")).build(),
						User.builder().userName("sonu").passWord(bCryptPasswordEncoder.encode("sahu")).build()).collect(Collectors.toList());
		final List<User> userList1 = userRepository.saveAll(userList);
		log.info(userList1);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtExampleApplication.class, args);
	}

}
