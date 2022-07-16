package com.sns.core.repository;

import com.sns.core.model.RenteeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RenteRequestRepository extends MongoRepository<RenteeRequest, String> {

    RenteeRequest findRenteeRequestById(String id);

    List<RenteeRequest> findAllByPropertyTypeLikeIgnoreCaseAndLocationsLikeIgnoreCase(String propertyType, String city);

    Page<RenteeRequest> findAllByRentee(String renteeId, Pageable pageable);
}
