package com.geektrust.backend.entities;

public enum PassengerType {
    ADULT(200), SENIOR_CITIZEN(100), KID(50);

    private long value;

    PassengerType(int val) {
        this.value = val;
    }

    public int getValue(){
        return (int) value;
    }
}