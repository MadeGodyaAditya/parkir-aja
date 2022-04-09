package com.mandiri.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mst_fee")
public class Fee {

    @Id
    @GeneratedValue(generator = "fee_id_generator")
    @GenericGenerator(name = "fee_id_generator", strategy="uuid")
    private String id;

    @NotNull
    private String parkingLotId;
    @NotNull
    private String category;
    @NotNull
    private Integer fee;

    public Fee() {
    }

    public String getId() {
        return id;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getCategory() {
        return category;
    }

    public Integer getFee() {
        return fee;
    }
}
