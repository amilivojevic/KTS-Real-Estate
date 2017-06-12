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



    @OneToMany(cascade = CascadeType.REMOVE)
    private List<RealEstate> realEstates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Advertisement> advertisements = new ArrayList<>();

    public Owner(String username, String password, String email, Role role, String name, String surname, Date birthDate, String phoneNumber, Location address, String accountNumber, String imageUrl) {
        super(username, password, email, role, name, surname, birthDate, phoneNumber, address, accountNumber, imageUrl);
    }

    public Owner(List<RealEstate> realEstates, List<Advertisement> advertisements) {
        this.realEstates = realEstates;
        this.advertisements = advertisements;
    }

    public Owner() {
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
