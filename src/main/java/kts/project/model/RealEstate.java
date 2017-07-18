package kts.project.model;

import kts.project.model.enumerations.HeatingType;
import kts.project.model.enumerations.RealEstateType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent RealEstate class
 */
@Entity
public class RealEstate {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "furniture", nullable = false)
    private boolean furniture;

    @Column(name = "parking", nullable = false)
    private boolean parking;

    @Column(name = "area", nullable = false)
    private double area;

    @Column(name = "constructionYear", nullable = false)
    private String constructionYear;

    @Column(name = "roomsNumber", nullable = false)
    private int roomsNumber;

    @Column(name = "bathroomsNumber", nullable = false)
    private int bathroomsNumber;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Location address;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private Owner owner;

    @NotNull
    @Enumerated(EnumType.STRING)
    private HeatingType heatingType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RealEstateType rs_type;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Advertisement> advertisements = new ArrayList<>();

    public RealEstate() {
    }

    public RealEstate(String description, String imageUrl, boolean furniture, boolean parking, double area, String constructionYear, int roomsNumber, int bathroomsNumber, Location address, Owner owner, HeatingType heatingType, RealEstateType rs_type, List<Advertisement> advertisements) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.furniture = furniture;
        this.parking = parking;
        this.area = area;
        this.constructionYear = constructionYear;
        this.roomsNumber = roomsNumber;
        this.bathroomsNumber = bathroomsNumber;
        this.address = address;
        this.owner = owner;
        this.heatingType = heatingType;
        this.rs_type = rs_type;
        this.advertisements = advertisements;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
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

    public RealEstateType getRs_type(){ return rs_type;}

    public void setRs_type(RealEstateType rs_type){ this.rs_type = rs_type;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
