package com.geektrust.backend.entities;

import java.util.HashMap;
import java.util.Map;
public class Station {
    Map<PassengerType, Integer> passengerTypeCount;
    double totalCollection;
    double totalDiscount;

    public Station(){
        passengerTypeCount = new HashMap<>();
        passengerTypeCount.put(PassengerType.ADULT,0);
        passengerTypeCount.put(PassengerType.SENIOR_CITIZEN,0);
        passengerTypeCount.put(PassengerType.KID,0);
        totalCollection = 0;
        totalDiscount = 0;
    }

    public Map<PassengerType, Integer> getPassengerTypeCount() {
        return passengerTypeCount;
    }

    public void setPassengerTypeCount(Map<PassengerType, Integer> passengerTypeCount) {
        this.passengerTypeCount = passengerTypeCount;
    }

    public double getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(double totalCollection) {
        this.totalCollection = totalCollection;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

}
