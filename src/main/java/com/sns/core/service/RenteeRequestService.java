package com.sns.core.service;

import com.sns.core.dto.RenteeRequestDto;
import com.sns.core.model.RenteeRequest;

public interface RenteeRequestService {

    RenteeRequest addRenteeRequest(RenteeRequestDto requestDto);

    RenteeRequest updateRenteeRequest(String requestId, RenteeRequestDto requestDto);
}
