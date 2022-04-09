package com.mandiri.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mst_parking_owner")
public class ParkingOwner {

    @Id
    @GeneratedValue(generator = "parking_owner_id_generator")
    @GenericGenerator(name = "parking_owner_id_generator", strategy="uuid")
    private String id;

    @NotNull
    private String idCard;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;

    public ParkingOwner() {
    }

    public String getId() {
        return id;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
