package com.sns.core.service;

import com.sns.core.model.House;

import java.util.List;

public interface PropertyService {

    List<House> getHouseByRenterId(String renterId);
}
