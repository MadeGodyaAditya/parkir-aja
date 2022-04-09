package com.mandiri.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "log_report")
public class Report {

    @Id
    @GeneratedValue(generator = "report_id_generator")
    @GenericGenerator(name = "report_id_generator", strategy="uuid")
    private String id;

    @NotNull
    private String parkingLotId;
    @NotNull
    private String licensePlate;
    @NotNull
    private Timestamp entrance;
    @NotNull
    private Timestamp exit;
    @NotNull
    private Double timeSpent;
    @NotNull
    private Integer fee;
    @NotNull
    private Integer totalFee;

    public Report() {
    }

    public String getId() {
        return id;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Timestamp getEntrance() {
        return entrance;
    }

    public Timestamp getExit() {
        return exit;
    }

    public Double getTimeSpent() {
        return timeSpent;
    }

    public Integer getFee() {
        return fee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }
}
