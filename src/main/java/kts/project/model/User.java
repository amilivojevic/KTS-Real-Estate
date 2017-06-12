package kts.project.model;


import com.sun.istack.internal.NotNull;
import kts.project.model.enumerations.Role;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    //username, password, email, role
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Authority authority;

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

    public User(String username, String password, String email, Role role, String name, String surname, Date birthDate, String phoneNumber, Location address, String accountNumber, String imageUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountNumber = accountNumber;
        this.imageUrl = imageUrl;
    }

    public User() {
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
