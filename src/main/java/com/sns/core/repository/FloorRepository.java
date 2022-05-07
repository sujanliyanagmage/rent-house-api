package com.sns.core.repository;

import com.sns.core.model.Floor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FloorRepository extends MongoRepository<Floor, String> {
    List<Floor> findAllByIdIn(List<String> ids);
}
