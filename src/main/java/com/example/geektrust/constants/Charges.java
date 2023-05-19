package com.example.geektrust.constants;

public enum Charges {
    ADULT(200),SENIOR_CITIZEN(100),KID(50);

    private final int value;
    Charges(int newValue) {
        value=newValue;
    }

    public int getValue() {
        return value;
    }
}
