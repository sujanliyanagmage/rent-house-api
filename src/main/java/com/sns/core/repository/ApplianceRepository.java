package com.sns.core.repository;

import com.sns.core.model.Appliance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplianceRepository extends MongoRepository<Appliance, String> {

}
