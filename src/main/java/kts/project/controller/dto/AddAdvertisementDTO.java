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

    protected String type;

    //id od nekretnine!!!
    private long id;

    private String currency;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
