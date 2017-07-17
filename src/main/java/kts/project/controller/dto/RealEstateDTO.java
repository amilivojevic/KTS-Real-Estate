package kts.project.controller.dto;

import kts.project.model.Advertisement;
import kts.project.model.Location;
import kts.project.model.Owner;
import kts.project.model.RealEstate;
import kts.project.model.enumerations.HeatingType;
import kts.project.model.enumerations.RealEstateType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 13-Jul-17.
 */
public class RealEstateDTO {


    protected String description;

    protected String imageUrl;

    protected boolean furniture;

    protected boolean parking;

    protected double area;

    protected String constructionYear;

    protected int roomsNumber;

    protected int bathroomsNumber;

    protected String city;

    protected String cityArea;

    protected String street;

    protected String streetNumber;

    protected String state;

    protected String zipCode;

    protected String heatingType;

    protected String rs_type;




    public RealEstateDTO() {
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public int getBathroomsNumber() {
        return bathroomsNumber;
    }

    public void setBathroomsNumber(int bathroomsNumber) {
        this.bathroomsNumber = bathroomsNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public String getRs_type() {
        return rs_type;
    }

    public void setRs_type(String rs_type) {
        this.rs_type = rs_type;
    }
}
