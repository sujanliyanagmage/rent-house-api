package com.sns.core.service;

import com.sns.core.model.House;
import com.sns.core.model.RenteeRequest;

public interface PropertyValidationService {

    /**
     * Calculates the property value of the house.
     *
     * @param house
     * @return : calculated value.
     */
    Double calculatePropertyValue(House house);

    /**
     * Calculates the request value of the house.
     *
     * @param request
     * @return : calculated value.
     */
    Double calculateRenteeRequestValue(RenteeRequest request);
}
