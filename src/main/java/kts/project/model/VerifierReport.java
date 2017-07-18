package kts.project.model;

import kts.project.model.enumerations.BanningReason;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * This class represent VerifierReport class
 */
@Entity
public class VerifierReport {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BanningReason banningReason;

    @ManyToOne
    @JoinColumn(name = "advertisement", nullable = false)
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "verifier", nullable = false)
    private User verifier;

    public VerifierReport() {
    }

    public VerifierReport(String description, Date date, BanningReason banningReason, Advertisement advertisement, User verifier) {
        this.description = description;
        this.date = date;
        this.banningReason = banningReason;
        this.advertisement = advertisement;
        this.verifier = verifier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BanningReason getBanningReason() {
        return banningReason;
    }

    public void setBanningReason(BanningReason banningReason) {
        this.banningReason = banningReason;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public User getVerifier() {
        return verifier;
    }

    public void setVerifier(User verifier) {
        this.verifier = verifier;
    }
}
