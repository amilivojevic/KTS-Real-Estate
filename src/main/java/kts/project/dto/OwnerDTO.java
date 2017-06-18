package kts.project.dto;

import kts.project.model.Advertisement;
import kts.project.model.Owner;
import kts.project.model.RealEstate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 18-Jun-17.
 */
public class OwnerDTO extends UserDTO {

    protected List<RealEstate> realEstates = new ArrayList<>();

    protected List<Advertisement> advertisements = new ArrayList<>();

    public OwnerDTO(Owner owner) {
        id = owner.getId();
        username = owner.getUsername();
        password = owner.getPassword();
        email = owner.getEmail();
        role = owner.getRole();
        authority = owner.getAuthority();
        name = owner.getName();
        surname = owner.getSurname();
        birthDate = owner.getBirthDate();
        phoneNumber = owner.getPhoneNumber();
        address = owner.getAddress();
        accountNumber = owner.getAccountNumber();
        imageUrl = owner.getImageUrl();
    }

    public OwnerDTO() {
    }

    public List<RealEstate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(List<RealEstate> realEstates) {
        this.realEstates = realEstates;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}