package kts.project.controller.dto;

import kts.project.model.Authority;
import kts.project.model.User;
import kts.project.model.enumerations.Role;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * Created by Nina on 18-Jul-17.
 */
public class UserDTO2 {

    private long id;
    private String username;
    private String email;
    private Role role;
    private Authority authority;
    private String name;
    private String surname;
    private Date birthDate;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String accountNumber;
    private String imageUrl;

    public UserDTO2() {
    }

    public UserDTO2(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.authority = user.getAuthority();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.birthDate = user.getBirthDate();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.accountNumber = user.getAccountNumber();
        this.imageUrl = user.getImageUrl();
    }

    public UserDTO2(long id, String username, String email, Role role, Authority authority, String name, String surname, Date birthDate, String phoneNumber, String address, String city, String country, String accountNumber, String imageUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.authority = authority;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.accountNumber = accountNumber;
        this.imageUrl = imageUrl;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
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
}
