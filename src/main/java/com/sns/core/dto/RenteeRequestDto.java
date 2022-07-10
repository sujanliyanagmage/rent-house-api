package com.sns.core.dto;

import java.util.List;

public class RenteeRequestDto {

    private List<String> locations;
    private String propertyType;
    private Double homeSize;
    private Double lotSize;
    private String yom;
    private Integer noOfBedRooms;
    private Integer noOfBathRooms;
    private String contactName;
    private String email;
    private String phone;
    private List<String> appliances;
    private List<String> floors;
    private List<String> parkingTypes;
    private String listingDescription;
    private Double askingPriceRange;
    private String listingSummery;
    private String payment;

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Double getHomeSize() {
        return homeSize;
    }

    public void setHomeSize(Double homeSize) {
        this.homeSize = homeSize;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<String> appliances) {
        this.appliances = appliances;
    }

    public List<String> getFloors() {
        return floors;
    }

    public void setFloors(List<String> floors) {
        this.floors = floors;
    }

    public List<String> getParkingTypes() {
        return parkingTypes;
    }

    public void setParkingTypes(List<String> parkingTypes) {
        this.parkingTypes = parkingTypes;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public Double getAskingPriceRange() {
        return askingPriceRange;
    }

    public void setAskingPriceRange(Double askingPriceRange) {
        this.askingPriceRange = askingPriceRange;
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
}
