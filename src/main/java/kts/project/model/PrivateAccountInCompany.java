package kts.project.model;

import javax.persistence.*;

/**
 * This class represent PrivateAccountInCompany class
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

    @Column(name = "approved", nullable = false)
    private boolean approved;

    public PrivateAccountInCompany(Owner owner, Company company) {
        this.owner = owner;
        this.company = company;
    }

    public PrivateAccountInCompany() {
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PrivateAccountInCompany{" +
                "id=" + id +
                ", ownerID=" + owner.getId() +
                ", companyID=" + company.getId() +
                ", approved=" + approved +
                '}';
    }
}
