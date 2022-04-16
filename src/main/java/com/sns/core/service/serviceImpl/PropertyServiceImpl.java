package com.sns.core.service.serviceImpl;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.House;
import com.sns.core.repository.HouseRepository;
import com.sns.core.service.PropertyService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<House> getHouseByRenterId(String renterId, Pageable pageable) {
        return houseRepository.findHouseByRenter(renterId,pageable);
    }

    @Override
    public List<House> getAllHouses(Pageable pageable) {
        return houseRepository.findAll(pageable).getContent();
    }

    @Override
    public House addPropertyTOCustomer(PropertyStageOneRequestDto propertyDto) {
        House house = modelMapper.map(propertyDto, House.class);
        return houseRepository.save(house);
    }

    @Override
    public House updatePropertyData(String propertyId,PropertyUpdateRequestDto propertyDto) {
        House houseById = houseRepository.findHouseById(propertyId);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(propertyDto,houseById);
        return houseRepository.save(houseById);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
