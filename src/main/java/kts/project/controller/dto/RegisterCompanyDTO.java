package kts.project.controller.dto;

import java.util.Date;

/**
 * Created by Sandra on 6/12/2017.
 */
public class RegisterCompanyDTO {

    private String type;
    private String role;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Date birthDate;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String accountNumber;
    private String imageUrl;
    private String pib;
    private String site;
    private String fax;

    @Override
    public String toString() {
        return "RegisterCompanyDTO{" +
                "type='" + type + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address + '\'' +
                ", city=" + city + '\'' +
                ", country=" + country + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", pib='" + pib + '\'' +
                ", site='" + site + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }

    public RegisterCompanyDTO() {
    }

    public RegisterCompanyDTO(String type, String role, String username, String password, String email, String name, String surname, Date birthDate, String phoneNumber, String address, String city, String country, String accountNumber, String imageUrl, String pib, String site, String fax) {
        this.type = type;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.accountNumber = accountNumber;
        this.imageUrl = imageUrl;
        this.pib = pib;
        this.site = site;
        this.fax = fax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
