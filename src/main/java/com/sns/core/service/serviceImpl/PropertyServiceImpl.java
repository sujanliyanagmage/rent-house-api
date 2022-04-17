package com.sns.core.service.serviceImpl;

import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.House;
import com.sns.core.model.User;
import com.sns.core.repository.HouseRepository;
import com.sns.core.repository.UserRepository;
import com.sns.core.service.PropertyService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @Override
    public List<House> getHouseByRenterId(String renterId, Pageable pageable) {
        return houseRepository.findHouseByRenter(renterId, pageable);
    }

    @Override
    public List<House> getAllHouses(Pageable pageable) {
        return houseRepository.findAll(pageable).getContent();
    }

    @Override
    public House addPropertyTOCustomer(PropertyStageOneRequestDto propertyDto) {
        House house = modelMapper.map(propertyDto, House.class);
        return houseRepository.save(house);
    }

    @Override
    public House updatePropertyData(String propertyId, PropertyUpdateRequestDto propertyDto) {
        String userId = getLoginUserDetails().getId();
        House houseById = houseRepository.findHouseById(propertyId);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(propertyDto, houseById);
        houseById.setRenter(userId);
        return houseRepository.save(houseById);
    }

    @Override
    public ResponseEntity<?> uploadImagesToCustomerProperty(String propertyId, List<MultipartFile> multipartFile) {

        User loginUserDetails = getLoginUserDetails();
        String path = loginUserDetails.getId() + "\\" + propertyId + "\\videos\\";
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
                Path directories = Files.createDirectories(Paths.get(path));
                file.transferTo(new File("C:\\upload\\" + fileName));
                uploadedFilePaths.add(path + fileName);
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
