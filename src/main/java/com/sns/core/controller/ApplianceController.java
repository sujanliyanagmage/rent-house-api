package com.sns.core.controller;

import com.sns.core.dto.ApplianceCollectionDto;
import com.sns.core.service.ApplianceCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {
    @Autowired
    private ApplianceCollectionService applianceCollectionService;

    @GetMapping
    public ApplianceCollectionDto getAllProperties() {
        return applianceCollectionService.getAllApplianceCollection();
    }
}
