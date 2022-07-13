package com.sns.core.service.serviceImpl;

import com.sns.core.dto.RenteeRequestDto;
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
import org.springframework.stereotype.Service;

import java.util.Date;
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
        String userId = propertyService.getLoginUserDetails().getId();
        RenteeRequest request = requestRepository.findRenteeRequestById(requestId);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(requestDto, request);

        List<Floor> floors = floorRepository.findAllByIdIn(requestDto.getFloors());
        if (floors != null && floors.size() > 0) {
            request.setFloors(floors);
        }
        List<Appliance> appliances = applianceRepository.findAllByIdIn(requestDto.getAppliances());
        if (appliances != null && appliances.size() > 0) {
            request.setAppliances(appliances);
        }
        List<Parking> parkings = parkingRepository.findAllByIdIn(requestDto.getParkingTypes());
        if (parkings != null && parkings.size() > 0) {
            request.setParkingTypes(parkings);
        }

        Double propertyValue = propertyValidationService.calculatePropertyValue(request);
        request.setValuePercentage(propertyValue);
        return requestRepository.save(request);
    }
}
