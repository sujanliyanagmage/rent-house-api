package com.sns.core.dto;

import com.sns.core.model.Appliance;
import com.sns.core.model.Floor;
import com.sns.core.model.Parking;

import java.io.Serializable;
import java.util.List;

public class PropertyUpdateRequestDto implements Serializable {
    private String propertyType;
    private String size;
    private String yom;
    private Integer noOfBedRooms;
    private Integer noOfBathRooms;
    private String contactName;
    private String email;
    private String phoneNo;
    private List<Appliance> appliances;
    private List<Floor> floors;
    private List<Parking> parkingTypes;
    private String listingSummery;
    private String payment;
    private String photosPath;
    private String videosPath;
    private String listingDescription;
    private String renter;
    private List<String> interestedRentees;


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

    public Integer getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(Integer noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public Integer getNoOfBathRooms() {
        return noOfBathRooms;
    }

    public void setNoOfBathRooms(Integer noOfBathRooms) {
        this.noOfBathRooms = noOfBathRooms;
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

    public String getPhotosPath() {
        return photosPath;
    }

    public void setPhotosPath(String photosPath) {
        this.photosPath = photosPath;
    }

    public String getVideosPath() {
        return videosPath;
    }

    public void setVideosPath(String videosPath) {
        this.videosPath = videosPath;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

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
}
