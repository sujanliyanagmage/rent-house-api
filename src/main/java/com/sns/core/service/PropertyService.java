package com.sns.core.service;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.House;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {

    List<House> getHouseByRenterId(String renterId, Pageable pageable);

    List<House> getAllHouses(Pageable pageable);

    House addPropertyTOCustomer(PropertyStageOneRequestDto propertyDto);

    House updatePropertyData(String propertyId,PropertyUpdateRequestDto propertyDto);

    ResponseEntity<?> uploadImagesToCustomerProperty(String propertyId, List<MultipartFile> multipartFile);

    ResponseEntity<?> uploadVideosToCustomerProperty(String propertyId,List<MultipartFile> multipartFile);
}
