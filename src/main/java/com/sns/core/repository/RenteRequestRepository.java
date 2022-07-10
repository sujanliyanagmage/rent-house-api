package com.sns.core.repository;

import com.sns.core.model.Appliance;
import com.sns.core.model.RenteeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RenteRequestRepository extends MongoRepository<RenteeRequest, String> {

    RenteeRequest findRenteeRequestById(String id);
}
