package com.sns.core.service.serviceImpl;

import com.sns.core.model.House;
import com.sns.core.model.RenteeRequest;
import com.sns.core.service.PropertyValidationService;
import org.springframework.stereotype.Service;

@Service("propertyValidationService")
public class PropertyValidationServiceImpl implements PropertyValidationService {
    /**
     * Calculates the property value of the house.
     *
     * @param house
     * @return : calculated value.
     */
    @Override
    public Double calculatePropertyValue(House house) {
        return null;
    }

    /**
     * Calculates the request value of the house.
     *
     * @param request
     * @return : calculated value.
     */
    @Override
    public Double calculatePropertyValue(RenteeRequest request) {
        return null;
    }
}
