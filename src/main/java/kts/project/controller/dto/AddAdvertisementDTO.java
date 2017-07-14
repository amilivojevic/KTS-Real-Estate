package kts.project.controller.dto;

import kts.project.model.RealEstate;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;

import java.util.Date;

/**
 * Created by Nina on 14-Jul-17.
 */
public class AddAdvertisementDTO {


    protected String title;

    protected float price;

    protected Date endingDate;

    protected String phoneNumber;

    protected AdvertisementType type;

    private long id;

    private Currency currency;

    private String token;

    public AddAdvertisementDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AdvertisementType getType() {
        return type;
    }

    public void setType(AdvertisementType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
