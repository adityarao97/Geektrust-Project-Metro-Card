package com.geektrust.backend.services;

import com.geektrust.backend.entities.*;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IStationRepository;

import java.util.Map;

public class MetroServiceImpl implements IMetroService {

    private final IMetroCardRepository metroCardRepository;
    private final IStationRepository stationRepository;

    public MetroServiceImpl(IMetroCardRepository metroCardRepository, IStationRepository stationRepository) {
        this.metroCardRepository = metroCardRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public void balance(String id, int amount) {
        metroCardRepository.save(id, amount);
    }

    private int calculateFareAmount(PassengerType passengerType, MetroCard metroCard, StationType stationType) {
        int fare = passengerType.getValue();
        if (metroCard.getLastStation() != null && metroCard.getLastStation() != stationType && !metroCard.isRoundTrip()) {
            fare = fare / 2;
            stationRepository.updateTotalCollectedDiscount(stationType, fare);
            metroCard.setRoundTrip(true);
        }
        metroCard.setLastStation(stationType);
        return fare;
    }

    @Override
    public void checkIn(String id, PassengerType passengerType, StationType stationType) {
        MetroCard metroCard = metroCardRepository.fetchMetroCardByID(id);
        int fare = calculateFareAmount(passengerType, metroCard, stationType);
        if (fare > metroCard.getBalance()) {
            int rechargeAmount = fare - metroCard.getBalance();
            metroCard.setBalance(metroCard.getBalance() + rechargeAmount);
            double platFormFee = 0.02 * rechargeAmount;
            stationRepository.updateTotalCollectedAmount(stationType, platFormFee);
        }
        metroCard.setBalance(metroCard.getBalance() - fare);
        stationRepository.updateTotalCollectedAmount(stationType, fare);
        stationRepository.updatePassengerCount(stationType, passengerType);
    }

    @Override
    public void printSummary(){
        Map<StationType, Station> stationSummary = stationRepository.fetchStationSummary();
        Station centralSummary = stationSummary.get(StationType.CENTRAL);
        Station airportSummary = stationSummary.get(StationType.AIRPORT);
        System.out.println("TOTAL_COLLECTION " + StationType.CENTRAL + " " + (int)centralSummary.getTotalCollection() + " " + (int)centralSummary.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        stationRepository.sortAndPrintPassengerSummary(StationType.CENTRAL);
        System.out.println("TOTAL_COLLECTION " + StationType.AIRPORT + " " + (int)airportSummary.getTotalCollection() + " " + (int)airportSummary.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        stationRepository.sortAndPrintPassengerSummary(StationType.AIRPORT);
    }
}
