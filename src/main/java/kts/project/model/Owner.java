package kts.project.model;



import kts.project.model.enumerations.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
public class Owner extends User {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Location address;

    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<RealEstate> realEstates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Advertisement> advertisements = new ArrayList<>();

    public Owner(String username, String password, String email, Role role, String name, String surname, Date birthDate,
                 String phoneNumber, Location address, String accountNumber, String imageUrl, List<RealEstate> realEstates,
                 List<Advertisement> advertisements) {
        super(username, password, email, role);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountNumber = accountNumber;
        this.imageUrl = imageUrl;
        this.realEstates = realEstates;
        this.advertisements = advertisements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
