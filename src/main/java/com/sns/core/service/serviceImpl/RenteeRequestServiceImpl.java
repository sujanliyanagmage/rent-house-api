package com.sns.core.service.serviceImpl;

import com.sns.core.dto.RenteeRequestDto;
import com.sns.core.dto.RenteeRequestResponseDto;
import com.sns.core.dto.RequestResponseDto;
import com.sns.core.model.*;
import com.sns.core.repository.*;
import com.sns.core.service.PropertyService;
import com.sns.core.service.PropertyValidationService;
import com.sns.core.service.RenteeRequestService;
import com.sns.core.util.PropertyStatus;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("renteeService")
public class RenteeRequestServiceImpl implements RenteeRequestService {

    @Autowired
    private RenteRequestRepository requestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment env;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyValidationService propertyValidationService;

    @Autowired
    private HouseRepository houseRepository;

    /**
     * Creates rentee request to search property.
     *
     * @param requestDto
     * @return
     */
    @Override
    public RenteeRequest addRenteeRequest(RenteeRequestDto requestDto) {
        RenteeRequest request = modelMapper.map(requestDto, RenteeRequest.class);
        request.setStatus(PropertyStatus.IN_PROGRESS);
        request.setPostedDate(new Date());
        String userId = propertyService.getLoginUserDetails().getId();
        request.setRentee(userId);
        return requestRepository.save(request);
    }

    /**
     * update rentee request with stepper details.
     *
     * @param requestId
     * @param requestDto
     * @return
     */
    @Override
    public RenteeRequest updateRenteeRequest(String requestId, RenteeRequestDto requestDto) {
        RenteeRequest request = requestRepository.findRenteeRequestById(requestId);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(requestDto, request);

        List<Floor> floors = floorRepository.findAllByIdIn(requestDto.getFloors());
        if (floors != null && !floors.isEmpty()) {
            request.setFloors(floors);
        }
        List<Appliance> appliances = applianceRepository.findAllByIdIn(requestDto.getAppliances());
        if (appliances != null && !appliances.isEmpty()) {
            request.setAppliances(appliances);
        }
        List<Parking> parkings = parkingRepository.findAllByIdIn(requestDto.getParkingTypes());
        if (parkings != null && !parkings.isEmpty()) {
            request.setParkingTypes(parkings);
        }

        Double propertyValue = propertyValidationService.calculateRenteeRequestValue(request);
        request.setValuePercentage(propertyValue);
        return requestRepository.save(request);
    }

    /**
     * Gets all rentee request by rentee Id value.
     *
     * @param renteeId
     * @param pageable
     * @return list of requests.
     */
    @Override
    public RenteeRequestResponseDto findAllRenteeRequestById(String renteeId, Pageable pageable) {
        Page<RenteeRequest> allByRentee = requestRepository.findAllByRentee(renteeId, pageable);
        List<RenteeRequest> content = allByRentee.getContent();
        List<RequestResponseDto> responseDtos = new ArrayList<>();
        content.forEach(rentee -> {
            //find matching properties..
            List<House> houses = findAllMatchiingProperties(rentee);
            RequestResponseDto request = modelMapper.map(rentee, RequestResponseDto.class);
            request.setMatchingProperties(houses);
            responseDtos.add(request);
        });
        RenteeRequestResponseDto dto = new RenteeRequestResponseDto();
        dto.setData(responseDtos);
        dto.setPageCount(allByRentee.getTotalPages());
        return dto;
    }

    /**
     * Gets all rentee requests.
     *
     * @param pageable
     * @return list of rentee requests.
     */
    @Override
    public RenteeRequestResponseDto findAllRenteeRequests(Pageable pageable) {
        Page<RenteeRequest> allByRentee = requestRepository.findAll(pageable);
        List<RenteeRequest> content = allByRentee.getContent();
        List<RequestResponseDto> responseDtos = new ArrayList<>();
        content.forEach(rentee -> {
            //find matching properties..
            List<House> houses = findAllMatchiingProperties(rentee);
            RequestResponseDto request = modelMapper.map(rentee, RequestResponseDto.class);
            request.setMatchingProperties(houses);
            responseDtos.add(request);
        });
        RenteeRequestResponseDto dto = new RenteeRequestResponseDto();
        dto.setData(responseDtos);
        dto.setPageCount(allByRentee.getTotalPages());
        return dto;
    }

    /**
     * Find all matching properties for the rentee's request.
     *
     * @param request
     * @return List of properties.
     */
    private List<House> findAllMatchiingProperties(RenteeRequest request) {
        if (request.getPropertyType() != null && request.getLocations() != null) {
            return houseRepository.findAllByPropertyTypeLikeIgnoreCaseAndCityLikeIgnoreCase(request.getPropertyType(), request.getLocations());
        }
        return new LinkedList<>();
    }
}
