package com.mandiri.dto;

public class ParkingLotSearchForm {
    private final String ownerId;
    private final String name;
    private final String address;
    private final Integer capacityBottomLimit;
    private final Integer capacityTopLimit;
    private final String category;
    private final Integer feeBottomLimit;
    private final Integer feeTopLimit;

    public ParkingLotSearchForm(String ownerId, String name, String address, Integer capacityBottomLimit, Integer capacityTopLimit, String category, Integer feeBottomLimit, Integer feeTopLimit) {
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.capacityBottomLimit = capacityBottomLimit;
        this.capacityTopLimit = capacityTopLimit;
        this.category = category;
        this.feeBottomLimit = feeBottomLimit;
        this.feeTopLimit = feeTopLimit;
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

    public Integer getCapacityBottomLimit() {
        return capacityBottomLimit;
    }

    public Integer getCapacityTopLimit() {
        return capacityTopLimit;
    }

    public String getCategory() {
        return category;
    }

    public Integer getFeeBottomLimit() {
        return feeBottomLimit;
    }

    public Integer getFeeTopLimit() {
        return feeTopLimit;
    }
}
