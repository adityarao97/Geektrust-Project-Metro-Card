package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StationRepositoryTest {

    @Test
    public void updateTotalCollectedAmountTest(){
        IStationRepository stationRepository = new StationRepository();
        stationRepository.updateTotalCollectedAmount(StationType.AIRPORT,500);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalCollection(),500);
        stationRepository.updateTotalCollectedAmount(StationType.AIRPORT,200);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalCollection(),700);
    }

    @Test
    public void updateTotalDiscountAmountTest(){
        IStationRepository stationRepository = new StationRepository();
        stationRepository.updateTotalCollectedDiscount(StationType.AIRPORT,500);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalDiscount(),500);
        stationRepository.updateTotalCollectedDiscount(StationType.AIRPORT,200);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalDiscount(),700);
    }

    @Test
    public void updateTotalPassengerCountTest(){
        IStationRepository stationRepository = new StationRepository();
        stationRepository.updatePassengerCount(StationType.AIRPORT, PassengerType.ADULT);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getPassengerTypeCount().get(PassengerType.ADULT),1);
        stationRepository.updatePassengerCount(StationType.AIRPORT, PassengerType.ADULT);
        Assertions.assertEquals(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getPassengerTypeCount().get(PassengerType.ADULT),2);
    }
}
