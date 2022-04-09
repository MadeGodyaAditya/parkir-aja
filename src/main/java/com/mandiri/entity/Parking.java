package com.mandiri.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "trx_parking")
public class Parking {

    @Id
    @GeneratedValue(generator = "parking_id_generator")
    @GenericGenerator(name = "parking_id_generator", strategy="uuid")
    private String id;

    @NotNull
    private String parkingLotId;
    @NotNull
    private String licensePlate;
    @NotNull
    private String type;
    @NotNull
    private Timestamp entrance;

    public Parking() {
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

    public String getType() {
        return type;
    }

    public Timestamp getEntrance() {
        return entrance;
    }
}
