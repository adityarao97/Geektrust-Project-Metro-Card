package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.*;

import java.util.*;

public class StationRepository implements IStationRepository {

    Map<StationType, Station> stationSummary;

    public StationRepository() {
        stationSummary = new HashMap<>();
        stationSummary.put(StationType.AIRPORT, new Station());
        stationSummary.put(StationType.CENTRAL, new Station());
    }

    @Override
    public void updateTotalCollectedAmount(StationType stationType, double amount) {
        Station station = stationSummary.get(stationType);
        station.setTotalCollection(station.getTotalCollection() + amount);
        stationSummary.put(stationType, station);
    }

    @Override
    public void updateTotalCollectedDiscount(StationType stationType, double discount) {
        Station station = stationSummary.get(stationType);
        station.setTotalDiscount(station.getTotalDiscount() + discount);
        stationSummary.put(stationType, station);
    }

    @Override
    public void updatePassengerCount(StationType stationType, PassengerType passengerType) {
        Station station = stationSummary.get(stationType);
        Map<PassengerType, Integer> map = station.getPassengerTypeCount();
        map.put(passengerType, map.get(passengerType) + 1);
        station.setPassengerTypeCount(map);
        stationSummary.put(stationType, station);
    }

    @Override
    public Map<StationType, Station> fetchStationSummary() {
        return stationSummary;
    }

    @Override
    public void sortAndPrintPassengerSummary(StationType stationType) {
        Station station = stationSummary.get(stationType);
        Map<PassengerType, Integer> passengerCountMap = station.getPassengerTypeCount();
        List<Map.Entry<PassengerType, Integer>> entryList = new ArrayList<>(passengerCountMap.entrySet());
        entryList.sort((entry1, entry2) -> {
            if (entry1.getValue() > entry2.getValue()) {
                return -1;
            } else if (entry1.getValue() < entry2.getValue()) {
                return 1;
            } else if (entry1.getKey().toString().charAt(0)>entry2.getKey().toString().charAt(0)) {
                return 1;
            } else {
                return -1;
            }
        });
        for (Map.Entry<PassengerType, Integer> entry : entryList) {
            if (entry.getValue() != 0) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
