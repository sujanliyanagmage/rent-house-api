package com.sns.core.repository;

import com.sns.core.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingRepository extends MongoRepository<Parking, String> {

    List<Parking> findAllByIdIn(List<String> ids);
}
