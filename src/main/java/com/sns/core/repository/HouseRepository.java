package com.sns.core.repository;

import com.sns.core.model.House;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HouseRepository extends MongoRepository<House, String> {

    List<House> findHouseByRenter(String renterId, Pageable pageable);

    House findHouseById(String propertyId);

    List<House> findAllByPropertyTypeLikeAndCityLike(String propertyType,String city);

    List<House> findAllByPropertyTypeLikeIgnoreCaseAndCityLikeIgnoreCase(String propertyType,List<String> cities);
}
