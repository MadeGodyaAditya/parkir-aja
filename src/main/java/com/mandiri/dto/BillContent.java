package com.mandiri.dto;

import java.sql.Timestamp;

public class BillContent <T>{
    private final T t;
    private final Timestamp exitTime;
    private final Integer price;

    public BillContent(T t, Timestamp exitTime, Integer price) {
        this.t = t;
        this.exitTime = exitTime;
        this.price = price;
    }

    public T getT() {
        return t;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public Integer getPrice() {
        return price;
    }
}
