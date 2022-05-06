package com.sns.core.service.serviceImpl;

import com.sns.core.dto.ApplianceCollectionDto;
import com.sns.core.dto.CategoryDto;
import com.sns.core.model.Appliance;
import com.sns.core.model.Floor;
import com.sns.core.model.Parking;
import com.sns.core.repository.ApplianceRepository;
import com.sns.core.repository.FloorRepository;
import com.sns.core.repository.ParkingRepository;
import com.sns.core.service.ApplianceCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplianceCollectionServiceImpl implements ApplianceCollectionService {

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private ApplianceRepository applianceRepository;
    @Autowired
    private ParkingRepository parkingRepository;

    @Override
    public ApplianceCollectionDto getAllApplianceCollection() {
        ApplianceCollectionDto applianceCollectionDto = new ApplianceCollectionDto();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        //Gets all floor details.
        CategoryDto dto = new CategoryDto();
        List<Floor> all = floorRepository.findAll();
        dto.setTitle("Floors");
        dto.setFeatures(all);
        categoryDtos.add(dto);

        //Gets all parking details.
        CategoryDto dto1 = new CategoryDto();
        List<Parking> all1 = parkingRepository.findAll();
        dto.setTitle("Parking");
        dto.setFeatures(all1);
        categoryDtos.add(dto1);

        //Gets all appliance details.
        CategoryDto dto2 = new CategoryDto();
        List<Appliance> all2 = applianceRepository.findAll();
        dto.setTitle("Appliance");
        dto.setFeatures(all2);
        categoryDtos.add(dto2);

        applianceCollectionDto.setCategories(categoryDtos);
        return applianceCollectionDto;
    }


}
