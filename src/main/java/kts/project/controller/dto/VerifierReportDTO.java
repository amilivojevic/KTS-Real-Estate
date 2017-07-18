package kts.project.controller.dto;

import kts.project.model.Advertisement;
import kts.project.model.enumerations.BanningReason;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * This class represent VerifierReportDTO class
 */
public class VerifierReportDTO {

    protected String description;

    protected Date date;

    protected String banningReason;

    protected long advertisementId;

    public VerifierReportDTO() {
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

    public String getBanningReason() {
        return banningReason;
    }

    public void setBanningReason(String banningReason) {
        this.banningReason = banningReason;
    }

    public long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(long advertisementId) {
        this.advertisementId = advertisementId;
    }
}
