package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class StationTest {

    @Test
    public void StationConstructorTest(){
        Station airport = new Station();
        Assertions.assertEquals(airport.getTotalCollection(),0);
        Assertions.assertEquals(airport.getTotalDiscount(),0);
        Assertions.assertEquals(airport.getPassengerTypeCount().get(PassengerType.ADULT),0);
        Assertions.assertEquals(airport.getPassengerTypeCount().get(PassengerType.SENIOR_CITIZEN),0);
        Assertions.assertEquals(airport.getPassengerTypeCount().get(PassengerType.KID),0);
    }
}
