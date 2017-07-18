package kts.project.controller.dto;

import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;

import java.util.Date;

/**
 * Created by Nina on 18-Jul-17.
 */
public class ApprovedPrivateAccountInCompanyDTO {

    private long id;
    private boolean approved;
    private Company company_id;
    private Owner owner_id;

    public ApprovedPrivateAccountInCompanyDTO() {
    }

    public ApprovedPrivateAccountInCompanyDTO(long id, boolean approved, Company company_id, Owner owner_id) {
        this.id = id;
        this.approved = approved;
        this.company_id = company_id;
        this.owner_id = owner_id;
    }

    public ApprovedPrivateAccountInCompanyDTO(PrivateAccountInCompany p) {
        this.id = p.getId();
        this.approved = p.isApproved();
        this.company_id = p.getCompany();
        this.owner_id = p.getOwner();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Company getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Company company_id) {
        this.company_id = company_id;
    }

    public Owner getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Owner owner_id) {
        this.owner_id = owner_id;
    }
}
