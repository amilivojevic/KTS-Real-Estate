package kts.project.dto;

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


    protected long id;

    protected String description;

    protected String imageUrl;

    protected boolean furniture;

    protected boolean parking;

    protected float area;

    protected String constructionYear;

    protected int roomsNumber;

    protected int bathroomsNumber;

    protected Location address;

    protected Owner owner;

    protected HeatingType heatingType;

    protected RealEstateType rs_type;

    private List<Advertisement> advertisements = new ArrayList<>();

    public RealEstateDTO(RealEstate realEstate) {
        id = realEstate.getId();
        description = realEstate.getDescription();
        imageUrl = realEstate.getImageUrl();
        furniture = realEstate.isFurniture();
        parking = realEstate.isParking();
        area = realEstate.getArea();
        constructionYear = realEstate.getConstructionYear();
        roomsNumber = realEstate.getRoomsNumber();
        bathroomsNumber = realEstate.getBathroomsNumber();
        address = realEstate.getAddress();
        owner = realEstate.getOwner();
        heatingType = realEstate.getHeatingType();
        rs_type = realEstate.getRs_type();
        advertisements = realEstate.getAdvertisements();

    }

    public RealEstateDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
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

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    public RealEstateType getRs_type() {
        return rs_type;
    }

    public void setRs_type(RealEstateType rs_type) {
        this.rs_type = rs_type;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
