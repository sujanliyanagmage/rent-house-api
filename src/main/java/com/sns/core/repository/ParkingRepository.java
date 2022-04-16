package com.sns.core.repository;

import com.sns.core.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingRepository extends MongoRepository<Parking, String> {
}
