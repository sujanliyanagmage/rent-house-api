package com.sns.core.user.repository;

import com.sns.core.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

}
