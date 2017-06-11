package kts.project.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Korisnik on 5/30/2017.
 */
@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "publishingDate", nullable = false)
    private Date publishingDate;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "advertisement", nullable = false)
    private Advertisement advertisement;

    public Review(String comment, int grade, Date publishingDate, Owner owner, Advertisement advertisement) {
        this.comment = comment;
        this.grade = grade;
        this.publishingDate = publishingDate;
        this.owner = owner;
        this.advertisement = advertisement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
