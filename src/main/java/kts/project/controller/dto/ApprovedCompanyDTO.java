package kts.project.controller.dto;

import kts.project.model.Company;

/**
 * Created by Sandra on 7/18/2017.
 */
public class ApprovedCompanyDTO {

    private long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String accountNumber;
    private String imageUrl;
    private boolean approved;

    public ApprovedCompanyDTO(long id, String username, String email, String name, String surname, String phoneNumber, String address, String city, String country, String accountNumber, String imageUrl, boolean approved) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.accountNumber = accountNumber;
        this.imageUrl = imageUrl;
        this.approved = approved;
    }

    public ApprovedCompanyDTO(Company u){

        this(u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getName(),
                u.getSurname(),
                u.getPhoneNumber(),
                u.getAddress(),
                u.getCity(),
                u.getCountry(),
                u.getAccountNumber(),
                u.getImageUrl(),
                u.isApproved());
    }

    public ApprovedCompanyDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
