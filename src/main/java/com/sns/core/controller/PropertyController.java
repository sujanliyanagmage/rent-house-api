package com.sns.core.controller;

import com.sns.core.dto.PropertyResponseDto;
import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.House;
import com.sns.core.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public PropertyResponseDto getAllProperties(Pageable pageable) {
        return propertyService.getAllHouses(pageable);
    }

    @PostMapping
    public House addProperty(@RequestBody PropertyStageOneRequestDto propertyDto) {
        return propertyService.addPropertyTOCustomer(propertyDto);
    }

    @PostMapping("{propertyId}/upload/images")
    public ResponseEntity<?> imageFileUpload(@PathVariable String propertyId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        return propertyService.uploadImagesToCustomerProperty(propertyId, files);
    }

    @PostMapping("{propertyId}/upload/videos")
    public ResponseEntity<?> videoFileUpload(@PathVariable String propertyId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        return propertyService.uploadVideosToCustomerProperty(propertyId, files);
    }

    @PutMapping("/{propertyId}")
    public House updateProperty(@PathVariable String propertyId, @RequestBody PropertyUpdateRequestDto propertyDto) {
        return propertyService.updatePropertyData(propertyId, propertyDto);
    }
}
