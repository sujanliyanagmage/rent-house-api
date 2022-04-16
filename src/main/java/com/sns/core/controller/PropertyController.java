package com.sns.core.controller;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.House;
import com.sns.core.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{renterId}")
    public List<House> getAllPropertiesByRenterId(@PathVariable String renterId, Pageable pageable) {
        return propertyService.getHouseByRenterId(renterId, pageable);
    }

    @GetMapping
    public List<House> getAllProperties(Pageable pageable) {
        return propertyService.getAllHouses(pageable);
    }

    @PostMapping
    public House addProperty(@RequestBody PropertyStageOneRequestDto propertyDto) {
        return propertyService.addPropertyTOCustomer(propertyDto);
    }

    @PutMapping("/{propertyId}")
    public House updateProperty(@PathVariable String propertyId,@RequestBody PropertyUpdateRequestDto propertyDto) {
        return propertyService.updatePropertyData(propertyId,propertyDto);
    }
}
