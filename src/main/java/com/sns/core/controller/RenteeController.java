package com.sns.core.controller;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.RenteeRequestDto;
import com.sns.core.model.House;
import com.sns.core.model.RenteeRequest;
import com.sns.core.service.RenteeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentee_requests")
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
}
