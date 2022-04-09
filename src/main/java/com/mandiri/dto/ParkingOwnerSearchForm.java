package com.mandiri.dto;

public class ParkingOwnerSearchForm {
    private final String idCard;
    private final String name;
    private final String address;
    private final String phoneNumber;

    public ParkingOwnerSearchForm(String idCard, String name, String address, String phoneNumber) {
        this.idCard = idCard;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
