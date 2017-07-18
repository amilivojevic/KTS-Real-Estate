package kts.project.controller.dto;

/**
 * This class represent FilterAdvertisementDTO class
 */
public class FilterAdvertisementDTO {
    protected String type;
    protected String location;
    protected double minPrice;
    protected double maxPrice;
    protected String currency;
    protected String area;
    protected int roomNu;
    protected int bathroomsNu;
    protected String heatingType;
    protected boolean furniture;
    protected boolean parking;

    @Override
    public String toString() {
        return "FilterAdvertisementDTO{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", currency='" + currency + '\'' +
                ", area=" + area +
                ", roomNu=" + roomNu +
                ", bathroomsNu=" + bathroomsNu +
                ", heatingType='" + heatingType + '\'' +
                ", furniture=" + furniture +
                ", parking=" + parking +
                '}';
    }

    public FilterAdvertisementDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getRoomNu() {
        return roomNu;
    }

    public void setRoomNu(int roomNu) {
        this.roomNu = roomNu;
    }

    public int getBathroomsNu() {
        return bathroomsNu;
    }

    public void setBathroomsNu(int bathroomsNu) {
        this.bathroomsNu = bathroomsNu;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
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
}
