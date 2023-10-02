package com.geektrust.backend.repositories;


import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.StationType;

import java.util.Map;

public interface IStationRepository {
    void updateTotalCollectedAmount(StationType stationType, double amount);
    void updateTotalCollectedDiscount(StationType stationType, double discount);
    void updatePassengerCount(StationType stationType, PassengerType passengerType);
    Map<StationType, Station> fetchStationSummary();
    void sortAndPrintPassengerSummary(StationType stationType);
}
