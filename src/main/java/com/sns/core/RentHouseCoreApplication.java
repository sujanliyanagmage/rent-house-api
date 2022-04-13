package com.sns.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sns.core.user.model.User;
import com.sns.core.user.repository.UserRepository;

@SpringBootApplication
public class RentHouseCoreApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RentHouseCoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.userRepository.findByUsername("rokin") == null) {
			User user = new User("Rokin Maharjan1", "rokin1", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));
			User user2 = new User("Rokin Maharjan2", "rokin2", passwordEncoder.encode("rokin123"), Arrays.asList("ADMIN"));
			ArrayList<User> collect = Stream.of(user, user2)
					.collect(Collectors.toCollection(ArrayList::new));
			this.userRepository.saveAll(collect);
		}
	}

}
