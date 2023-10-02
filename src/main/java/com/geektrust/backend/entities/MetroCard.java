package com.geektrust.backend.entities;

import java.util.Objects;

public class MetroCard extends BaseEntity {
    private int balance;
    private StationType stationType;
    private boolean isRoundTrip;

    public MetroCard(String id, int balance) {
        this.id = id;
        this.balance = balance;
        this.isRoundTrip = false;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public StationType getLastStation() {
        return stationType;
    }

    public void setLastStation(StationType stationType) {
        this.stationType = stationType;
    }

    public Boolean equals(MetroCard metroCard){
        if(metroCard==null || metroCard.getId()==null){
            return false;
        }
        return Objects.equals(this.balance, metroCard.getBalance()) && Objects.equals(this.id, metroCard.getId());
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }
}