package kts.project.model;

import javax.persistence.*;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
public class PrivateAccountInCompany {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "company", nullable = false)
    private Company company;

    public PrivateAccountInCompany(Owner owner, Company company) {
        this.owner = owner;
        this.company = company;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
