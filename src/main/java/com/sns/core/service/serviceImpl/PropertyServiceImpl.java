package com.sns.core.service.serviceImpl;

import com.sns.core.dto.PropertyResponseDto;
import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.*;
import com.sns.core.repository.*;
import com.sns.core.service.PropertyService;
import com.sns.core.util.PropertyStatus;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    public static final String VIDEO = "Video";
    public static final String IMAGE = "Image";
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Override
    public List<House> getHouseByRenterId(String renterId, Pageable pageable) {
        return houseRepository.findHouseByRenter(renterId, pageable);
    }

    @Override
    public PropertyResponseDto getAllHouses(Pageable pageable) {
        PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
        Page<House> all = houseRepository.findAll(pageable);
        List<House> content = all.getContent();
        int totalPages = all.getTotalPages();
        propertyResponseDto.setData(content);
        propertyResponseDto.setPageCount(totalPages);
        return propertyResponseDto;
    }

    @Override
    public House addPropertyTOCustomer(PropertyStageOneRequestDto propertyDto) {
        House house = modelMapper.map(propertyDto, House.class);
        house.setStatus(PropertyStatus.AVAILABLE);
        house.setPostedDate(new Date());
        return houseRepository.save(house);
    }

    @Override
    public House updatePropertyData(String propertyId, PropertyUpdateRequestDto propertyDto) {
        String userId = getLoginUserDetails().getId();
        House houseById = houseRepository.findHouseById(propertyId);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(propertyDto, houseById);
        houseById.setRenter(userId);

        List<Floor> floors = floorRepository.findAllByIdIn(propertyDto.getFloors());
        if (floors != null && floors.size() > 0) {
            houseById.setFloors(floors);
        }
        List<Appliance> appliances = applianceRepository.findAllByIdIn(propertyDto.getAppliances());
        if (appliances != null && appliances.size() > 0) {
            houseById.setAppliances(appliances);
        }
        List<Parking> parkings = parkingRepository.findAllByIdIn(propertyDto.getParkingTypes());
        if (parkings != null && parkings.size() > 0) {
            houseById.setParkingTypes(parkings);
        }
        return houseRepository.save(houseById);
    }

    @Override
    public ResponseEntity<?> uploadImagesToCustomerProperty(String propertyId, List<MultipartFile> multipartFile) {

        User loginUserDetails = getLoginUserDetails();
        String path = loginUserDetails.getId() + "\\" + propertyId + "\\images\\";
        uploadFiles(path, IMAGE, propertyId, multipartFile);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @Override
    public ResponseEntity<?> uploadVideosToCustomerProperty(String propertyId, List<MultipartFile> multipartFile) {

        User loginUserDetails = getLoginUserDetails();
        String path = loginUserDetails.getId() + "\\" + propertyId + "\\videos\\";
        uploadFiles(path, VIDEO, propertyId, multipartFile);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    private void uploadFiles(String path, String uploadType, String propertyId, List<MultipartFile> multipartFile) {
        List<String> uploadedFilePaths = new ArrayList<>();
        multipartFile.forEach(file -> {
            String fileName = file.getOriginalFilename();
            try {
                Path storageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files/"))
                        .toAbsolutePath().normalize();
                String fileUploadedLPath = storageLocation + "\\" + path;
                Files.createDirectories(Paths.get(fileUploadedLPath));
                file.transferTo(new File(fileUploadedLPath + fileName));
                uploadedFilePaths.add(storageLocation + path + fileName);
            } catch (Exception e) {
                throw new InternalError();
            }
            House houseById = houseRepository.findHouseById(propertyId);
            if (uploadType.equalsIgnoreCase(VIDEO)) {
                houseById.setVideosPath(uploadedFilePaths);
            } else {
                houseById.setPhotosPath(uploadedFilePaths);
            }
            houseRepository.save(houseById);
        });
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    private User getLoginUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userRepository.findByUsername(currentUserName);
        }
        throw new UsernameNotFoundException("--");
    }
}
