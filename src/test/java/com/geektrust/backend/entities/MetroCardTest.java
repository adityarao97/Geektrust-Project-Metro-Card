package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetroCardTest {

    @Test
    public void metroCardTestConstructor(){

        MetroCard metroCard = new MetroCard("MC1",200);
        Assertions.assertEquals(metroCard.getBalance(),200);
        Assertions.assertEquals(metroCard.getId(),"MC1");
        metroCard.setLastStation(StationType.AIRPORT);
        Assertions.assertEquals(metroCard.getLastStation(),StationType.AIRPORT);
    }
}
