package kts.project.model;

import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
public class Advertisement {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "announcementDate", nullable = false)
    private Date announcementDate;

    @Column(name = "updateDate", nullable = false)
    private Date updateDate;

    @Column(name = "endingDate", nullable = false)
    private Date endingDate;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AdvertisementType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AdvertisementState state;

    @ManyToOne
    @JoinColumn(name = "realEstate", nullable = false)
    private RealEstate realEstate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private Owner owner;

    public Advertisement() {
    }

    public Advertisement(String title, float price, Date announcementDate, Date updateDate, Date endingDate, String phoneNumber, List<Review> reviews, AdvertisementType type, AdvertisementState state, RealEstate realEstate, Currency currency, Owner owner) {
        this.title = title;
        this.price = price;
        this.announcementDate = announcementDate;
        this.updateDate = updateDate;
        this.endingDate = endingDate;
        this.phoneNumber = phoneNumber;
        this.reviews = reviews;
        this.type = type;
        this.state = state;
        this.realEstate = realEstate;
        this.currency = currency;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public AdvertisementType getType() {
        return type;
    }

    public void setType(AdvertisementType type) {
        this.type = type;
    }

    public AdvertisementState getState() {
        return state;
    }

    public void setState(AdvertisementState state) {
        this.state = state;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


}
