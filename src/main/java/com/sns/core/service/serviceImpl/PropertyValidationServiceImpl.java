package com.sns.core.service.serviceImpl;

import com.sns.core.model.*;
import com.sns.core.repository.ApplianceRepository;
import com.sns.core.service.PropertyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("propertyValidationService")
public class PropertyValidationServiceImpl implements PropertyValidationService {

    @Autowired
    private ApplianceRepository aplApplianceRepository;

    /**
     * Calculates the property value of the house.
     *
     * @param house
     * @return : calculated value.
     */
    @Override
    public Double calculatePropertyValue(House house) {
        double houseValue = calculateAppliancePercentage(house.getAppliances()) +
                calculateFloorPercentage(house.getFloors()) +
                calculateParkingPercentage(house.getParkingTypes()) +
                calculateBedRoomPercentage(house.getNoBedRooms()) +
                calculateBathRoomPercentage(house.getNoBathRooms());
        return houseValue;
    }

    /**
     * Calculates the request value of the house.
     *
     * @param request
     * @return : calculated value.
     */
    @Override
    public Double calculateRenteeRequestValue(RenteeRequest request) {
        double requestValue = calculateAppliancePercentage(request.getAppliances()) +
                calculateFloorPercentage(request.getFloors()) +
                calculateParkingPercentage(request.getParkingTypes()) +
                calculateBedRoomPercentage(request.getNoOfBedRooms()) +
                calculateBathRoomPercentage(request.getNoOfBathRooms());
        return requestValue;
    }


    /**
     * Appliances will have 10% from 30%
     *
     * @param appliances
     * @return
     */
    private double calculateAppliancePercentage(List<Appliance> appliances) {
        int all = aplApplianceRepository.findAll().size();
        return appliances != null ? (100 / all) * appliances.size() * 0.1 : 0;
    }

    /**
     * Floors will have 10% from 30%
     *
     * @param floors
     * @return
     */
    private double calculateFloorPercentage(List<Floor> floors) {
        return (floors != null && floors.size() > 0) ? 10 * 0.1: 0;
    }

    /**
     * Floors will have 10% from 30%
     *
     * @param parking
     * @return
     */
    private double calculateParkingPercentage(List<Parking> parking) {
        return (parking != null && parking.size() > 0) ? 20 * 0.2 : 0;
    }


    /**
     * BedRooms will have 10% from 20%
     *
     * @param bedRooms
     * @return
     */
    private double calculateBedRoomPercentage(Integer bedRooms) {
        return ((bedRooms != null && bedRooms >= 4 ) ? 100 : ((bedRooms != null)? ((100 / 4) * bedRooms) * 0.3:0));
    }


    /**
     * BathRooms will have 10% from 20%
     *
     * @param bathRooms
     * @return
     */
    private double calculateBathRoomPercentage(Integer bathRooms) {
        return ((bathRooms !=null && bathRooms >= 2 )? 100 : ((bathRooms !=null)?((100 / 2) * bathRooms ) * 0.3:0));
    }
}
