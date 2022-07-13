package com.sns.core.service;

import com.sns.core.dto.RenteeRequestDto;
import com.sns.core.model.RenteeRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeRequestService {

    RenteeRequest addRenteeRequest(RenteeRequestDto requestDto);

    RenteeRequest updateRenteeRequest(String requestId, RenteeRequestDto requestDto);

    List<RenteeRequest> findAllRenteeRequestById(String renteeId, Pageable pageable);
}
