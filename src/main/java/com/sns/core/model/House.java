package com.sns.core.model;

import com.sns.core.util.PropertyStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "houses")
public class House implements Serializable {
    @Id
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String propertyType;
    private String size;
    private String yom;
    private Integer noBedRooms;
    private Integer noBathRooms;
    private String contactName;
    private String email;
    private String phoneNo;
    @DBRef
    private List<Appliance> appliances;
    @DBRef
    private List<Floor> floors;
    @DBRef
    private List<Parking> parkingTypes;
    private String listingSummery;
    private String payment;
    private List<String> photosPath;
    private List<String> videosPath;
    private String listingDescription;
    private String renter;
    private List<String> interestedRentees;
    private String refVideoLink;

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    private Date postedDate;
    private PropertyStatus status;

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public List<String> getInterestedRentees() {
        return interestedRentees;
    }

    public void setInterestedRentees(List<String> interestedRentees) {
        this.interestedRentees = interestedRentees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getYom() {
        return yom;
    }

    public void setYom(String yom) {
        this.yom = yom;
    }

    public Integer getNoBedRooms() {
        return noBedRooms;
    }

    public void setNoBedRooms(Integer noBedRooms) {
        this.noBedRooms = noBedRooms;
    }

    public Integer getNoBathRooms() {
        return noBathRooms;
    }

    public void setNoBathRooms(Integer noBathRooms) {
        this.noBathRooms = noBathRooms;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Parking> getParkingTypes() {
        return parkingTypes;
    }

    public void setParkingTypes(List<Parking> parkingTypes) {
        this.parkingTypes = parkingTypes;
    }

    public String getListingSummery() {
        return listingSummery;
    }

    public void setListingSummery(String listingSummery) {
        this.listingSummery = listingSummery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<String> getPhotosPath() {
        return photosPath;
    }

    public void setPhotosPath(List<String> photosPath) {
        this.photosPath = photosPath;
    }

    public List<String> getVideosPath() {
        return videosPath;
    }

    public void setVideosPath(List<String> videosPath) {
        this.videosPath = videosPath;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public String getRefVideoLink() {
        return refVideoLink;
    }

    public void setRefVideoLink(String refVideoLink) {
        this.refVideoLink = refVideoLink;
    }
}
