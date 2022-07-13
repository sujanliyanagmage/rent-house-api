package com.sns.core.repository;

import com.sns.core.model.Appliance;
import com.sns.core.model.House;
import com.sns.core.model.RenteeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RenteRequestRepository extends MongoRepository<RenteeRequest, String> {

    RenteeRequest findRenteeRequestById(String id);

    List<RenteeRequest> findAllByPropertyTypeLikeAndLocationsContains(String propertyType, String city);
}
