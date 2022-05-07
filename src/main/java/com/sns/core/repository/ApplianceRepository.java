package com.sns.core.repository;

import com.sns.core.model.Appliance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplianceRepository extends MongoRepository<Appliance, String> {
    List<Appliance> findAllByIdIn(List<String> ids);
}
