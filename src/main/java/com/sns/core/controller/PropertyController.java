package com.sns.core.controller;

import com.sns.core.model.House;
import com.sns.core.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{renterId}")
    public List<House> getAllPropertiesByRenterId(@PathVariable String renterId) {
        return propertyService.getHouseByRenterId(renterId);
    }
}
