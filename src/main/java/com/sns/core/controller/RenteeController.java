package com.sns.core.controller;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.RenteeRequestDto;
import com.sns.core.dto.RenteeRequestResponseDto;
import com.sns.core.model.House;
import com.sns.core.model.RenteeRequest;
import com.sns.core.service.RenteeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentee_requests")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('RENTEE')")
public class RenteeController {

    @Autowired
    private RenteeRequestService renteeRequestService;


    @PostMapping
    public RenteeRequest addProperty(@RequestBody RenteeRequestDto requestDto) {
        return renteeRequestService.addRenteeRequest(requestDto);
    }

    @PutMapping("/{requestId}")
    public RenteeRequest addProperty(@PathVariable String requestId, @RequestBody RenteeRequestDto requestDto) {
        return renteeRequestService.updateRenteeRequest(requestId, requestDto);
    }

    @GetMapping("/{renteeId}")
    public RenteeRequestResponseDto getAllRenteeRequestByRenteeId(@PathVariable String renteeId, Pageable pageable) {
        return renteeRequestService.findAllRenteeRequestById(renteeId, pageable);
    }
}
