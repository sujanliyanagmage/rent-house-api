package com.sns.core.model;

import com.sns.core.util.PropertyStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "renteeRequests")
public class RenteeRequest {
    @Id
    private String id;
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
    @DBRef
    private List<Appliance> appliances;
    @DBRef
    private List<Floor> floors;
    @DBRef
    private List<Parking> parkingTypes;
    private String listingDescription;
    private Double askingPriceRange;
    private String listingSummery;
    private String payment;
    private Date postedDate;
    private PropertyStatus status;
    private Double valuePercentage;
    private String rentee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getValuePercentage() {
        return valuePercentage;
    }

    public void setValuePercentage(Double valuePercentage) {
        this.valuePercentage = valuePercentage;
    }

    public String getRentee() {
        return rentee;
    }

    public void setRentee(String rentee) {
        this.rentee = rentee;
    }
}
