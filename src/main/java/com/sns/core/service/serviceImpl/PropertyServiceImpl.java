package com.sns.core.service.serviceImpl;

import com.sns.core.model.House;
import com.sns.core.repository.HouseRepository;
import com.sns.core.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public List<House> getHouseByRenterId(String renterId) {
        return houseRepository.findHouseByRenter(renterId);
    }
}
