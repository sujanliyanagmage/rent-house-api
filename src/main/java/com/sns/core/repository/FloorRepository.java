package com.sns.core.repository;

import com.sns.core.model.Floor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FloorRepository extends MongoRepository<Floor, String> {
}
