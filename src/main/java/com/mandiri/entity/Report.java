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

    private String parkingLotId;
    private String licensePlate;
    private Timestamp entrance;
    private Timestamp exit;
    private Long timeSpent;
    private Integer fee;
    private Integer totalFee;

    public Report() {
    }

    public Report(String parkingLotId, String licensePlate, Timestamp entrance, Timestamp exit, Long timeSpent, Integer fee, Integer totalFee) {
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
        this.entrance = entrance;
        this.exit = exit;
        this.timeSpent = timeSpent;
        this.fee = fee;
        this.totalFee = totalFee;
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

    public Long getTimeSpent() {
        return timeSpent;
    }

    public Integer getFee() {
        return fee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }


}
