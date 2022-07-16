package com.sns.core.service.serviceImpl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.sns.core.dto.HouseResponseDto;
import com.sns.core.dto.PropertyResponseDto;
import com.sns.core.dto.PropertyStageOneRequestDto;
import com.sns.core.dto.PropertyUpdateRequestDto;
import com.sns.core.model.*;
import com.sns.core.repository.*;
import com.sns.core.service.PropertyService;
import com.sns.core.service.PropertyValidationService;
import com.sns.core.util.PropertyStatus;
import org.joda.time.DateTime;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    public static final String VIDEO = "Video";
    public static final String IMAGE = "Image";
    public static final String MATCH_RENTEE_IMAGES = "match-rentee-images";
    public static final String PREFIX = "yyyyMMddHHmmss";
    public static final String MATCH_RENTEE_VIDEOS = "match-rentee-videos";
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

    @Autowired
    private PropertyValidationService propertyValidationService;

    @Autowired
    private RenteRequestRepository requestRepository;

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
        List<HouseResponseDto> houseResponseDtos = new ArrayList<>();
        content.forEach(property -> {
            //find matching rentees..
            List<RenteeRequest> rentees = findAllMatchiingRenteeRequests(property);
            HouseResponseDto house = modelMapper.map(property, HouseResponseDto.class);
            house.setMatchingRentees(rentees);
            houseResponseDtos.add(house);
        });

        propertyResponseDto.setData(houseResponseDtos);
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
        if (floors != null && !floors.isEmpty()) {
            houseById.setFloors(floors);
        }
        List<Appliance> appliances = applianceRepository.findAllByIdIn(propertyDto.getAppliances());
        if (appliances != null && !appliances.isEmpty()) {
            houseById.setAppliances(appliances);
        }
        List<Parking> parkings = parkingRepository.findAllByIdIn(propertyDto.getParkingTypes());
        if (parkings != null && !parkings.isEmpty()) {
            houseById.setParkingTypes(parkings);
        }
        Double calculatePropertyValue = propertyValidationService.calculatePropertyValue(houseById);
        houseById.setValuePercentage(calculatePropertyValue);
        return houseRepository.save(houseById);
    }

    @Override
    public ResponseEntity<?> uploadImagesToCustomerProperty(String propertyId, List<MultipartFile> multipartFile) {

        //upload to S3 file
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIA3TJ6OKOX2JEQJUHW",
                "06J+3vho0Ne4+XbQAUqCxf789ODbr42icxtANprL"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        if (!s3client.doesBucketExist(MATCH_RENTEE_IMAGES)) {
            s3client.createBucket(MATCH_RENTEE_IMAGES);
        }
        List<String> imageUrls = new ArrayList<>();
        multipartFile.forEach(file -> {
            File file1 = null;
            try {
                file1 = convertMultiPartToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //creates unique file name.
            String fileName = DateTime.now().toString(PREFIX) + file.getOriginalFilename();
             s3client.putObject(
                    MATCH_RENTEE_IMAGES,
                    fileName, file1
            );
            URL url = s3client.getUrl(MATCH_RENTEE_IMAGES, fileName);
            imageUrls.add(String.valueOf(url));
        });

        House houseById = houseRepository.findHouseById(propertyId);
        houseById.setPhotosPath(imageUrls);
        houseRepository.save(houseById);
        return ResponseEntity.ok("Image file uploaded successfully.");
    }

    @Override
    public ResponseEntity<?> uploadVideosToCustomerProperty(String propertyId, List<MultipartFile> multipartFile) {

        //upload to S3 file
        AWSCredentials credentials = new BasicAWSCredentials(
                env.getProperty("aws.s3.key", "AKIA3TJ6OKOX2JEQJUHW"),
                env.getProperty("aws.s3.value", "06J+3vho0Ne4+XbQAUqCxf789ODbr42icxtANprL")
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        if (!s3client.doesBucketExist(MATCH_RENTEE_VIDEOS)) {
            s3client.createBucket(MATCH_RENTEE_VIDEOS);
        }
        List<String> videoUrls = new ArrayList<>();
        multipartFile.forEach(file -> {
            File file1 = null;
            try {
                file1 = convertMultiPartToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //creates unique file name.
            String fileName = DateTime.now().toString(PREFIX) + file.getOriginalFilename();

             s3client.putObject(
                    MATCH_RENTEE_VIDEOS,
                    fileName, file1
            );
            URL url = s3client.getUrl(MATCH_RENTEE_VIDEOS, fileName);
            videoUrls.add(String.valueOf(url));
        });

        House houseById = houseRepository.findHouseById(propertyId);
        houseById.setVideosPath(videoUrls);
        houseRepository.save(houseById);

        return ResponseEntity.ok("Video file uploaded successfully.");
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

    public User getLoginUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userRepository.findByUsername(currentUserName);
        }
        throw new UsernameNotFoundException("--");
    }

    /**
     * Collects matching rentees for given house based on location and property type.
     *
     * @param house
     * @return
     */
    private List<RenteeRequest> findAllMatchiingRenteeRequests(House house) {
        if (house.getPropertyType() != null && house.getCity() != null) {
            return requestRepository.findAllByPropertyTypeLikeIgnoreCaseAndLocationsLikeIgnoreCase(house.getPropertyType(), house.getCity());
        }
        return new LinkedList<>();
    }

    /**
     * converts given multipart file to file type.
     *
     * @param file
     * @return
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
