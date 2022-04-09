package com.mandiri.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mst_parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(generator = "parking_lot_id_generator")
    @GenericGenerator(name = "parking_lot_id_generator", strategy="uuid")
    private String id;

    @NotNull
    private String ownerId;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Double size;

    @NotNull
    private Integer capacity;

    public ParkingLot() {
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getSize() {
        return size;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
