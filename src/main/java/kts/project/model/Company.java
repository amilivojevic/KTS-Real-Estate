package kts.project.model;

import kts.project.model.enumerations.Role;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
public class Company extends Owner {

    @Column(name = "pib", nullable = false, unique = true)
    private String pib;

    @Column(name = "site", nullable = false)
    private String site;

    @Column(name = "fax", nullable = false)
    private String fax;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<PrivateAccountInCompany> workers = new ArrayList<>();

    public Company(String username, String password, String email, Role role, String name, String surname, Date birthDate, String phoneNumber, Location address, String accountNumber, String imageUrl) {
        super(username, password, email, role, name, surname, birthDate, phoneNumber, address, accountNumber, imageUrl);
    }

    public Company(String pib, String site, String fax, boolean approved) {
        this.pib = pib;
        this.site = site;
        this.fax = fax;
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public List<PrivateAccountInCompany> getWorkers() {
        return workers;
    }

    public void setWorkers(List<PrivateAccountInCompany> workers) {
        this.workers = workers;
    }

    public Company(String username, String password, String email, Role role, String name, String surname, Date birthDate, String phoneNumber, Location address, String accountNumber, String imageUrl, String pib, String site, String fax, List<PrivateAccountInCompany> workers) {
        super(username, password, email, role, name, surname, birthDate, phoneNumber, address, accountNumber, imageUrl);
        this.pib = pib;
        this.site = site;
        this.fax = fax;
        this.workers = workers;
    }

    public Company(List<RealEstate> realEstates, List<Advertisement> advertisements, String pib, String site, String fax, List<PrivateAccountInCompany> workers) {
        super(realEstates, advertisements);
        this.pib = pib;
        this.site = site;
        this.fax = fax;
        this.workers = workers;
    }

    public Company(String pib, String site, String fax, List<PrivateAccountInCompany> workers) {
        this.pib = pib;
        this.site = site;
        this.fax = fax;
        this.workers = workers;
    }

    public Company() {
    }

    public Company(String username, String password, String email, Role role, String name, String surname, Date birthDate, String phoneNumber, Location address, String accountNumber, String imageUrl, String pib, String site, String fax, boolean approved) {
        super(username, password, email, role, name, surname, birthDate, phoneNumber, address, accountNumber, imageUrl);
        this.pib = pib;
        this.site = site;
        this.fax = fax;
        this.approved = approved;
    }
}
