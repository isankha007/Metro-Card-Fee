package com.example.geektrust.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Station {
    private String name;
    private Integer totalAmount;

    private Integer discountGiven;


    public Station() {
        discountGiven=0;
        totalAmount=0;
    }

    SortedMap<String,Integer> passengerMap=new TreeMap<>();

    public Integer getDiscountGiven() {
        return discountGiven;
    }

    public void setDiscountGiven(Integer discountGiven) {
        this.discountGiven = discountGiven;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SortedMap<String, Integer> getPassengerMap() {
        return passengerMap;
    }

    public void setPassengerMap(SortedMap<String, Integer> passengerMap) {
        this.passengerMap = passengerMap;
    }


}
