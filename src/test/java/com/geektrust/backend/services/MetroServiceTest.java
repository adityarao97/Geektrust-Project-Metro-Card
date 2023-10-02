package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationType;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.StationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MetroServiceTest {

    IMetroCardRepository metroCardRepository = new MetroCardRepository();
    IStationRepository stationRepository = new StationRepository();
    IMetroService metroService = new MetroServiceImpl(metroCardRepository, stationRepository);
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    public void testBalance() {
        String id = "MC1";
        int amount = 600;
        metroService.balance(id, amount);
        Assertions.assertTrue(metroCardRepository.fetchMetroCardByID(id).equals(new MetroCard(id, amount)));
    }

    @Test
    public void testCheckInAndPrintSummary() {
        System.setOut(new PrintStream(outputStreamCaptor));
        String expectedOutput = "TOTAL_COLLECTION CENTRAL 300 0\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "ADULT 1\n" +
                "SENIOR_CITIZEN 1\n" +
                "TOTAL_COLLECTION AIRPORT 403 100\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "ADULT 2\n" +
                "KID 2";
        metroService.balance("MC1", 600);
        metroService.balance("MC2", 500);
        metroService.balance("MC3", 50);
        metroService.balance("MC4", 50);
        metroService.balance("MC5", 200);
        metroService.checkIn("MC1", PassengerType.ADULT, StationType.CENTRAL);
        metroService.checkIn("MC2", PassengerType.SENIOR_CITIZEN, StationType.CENTRAL);
        metroService.checkIn("MC1", PassengerType.ADULT, StationType.AIRPORT);
        metroService.checkIn("MC3", PassengerType.KID, StationType.AIRPORT);
        metroService.checkIn("MC4", PassengerType.ADULT, StationType.AIRPORT);
        metroService.checkIn("MC5", PassengerType.KID, StationType.AIRPORT);
        Assertions.assertEquals(300, stationRepository.fetchStationSummary().get(StationType.CENTRAL).getTotalCollection());
        Assertions.assertEquals(0, stationRepository.fetchStationSummary().get(StationType.CENTRAL).getTotalDiscount());
        Assertions.assertEquals(1, stationRepository.fetchStationSummary().get(StationType.CENTRAL).getPassengerTypeCount().get(PassengerType.ADULT));
        Assertions.assertEquals(1, stationRepository.fetchStationSummary().get(StationType.CENTRAL).getPassengerTypeCount().get(PassengerType.SENIOR_CITIZEN));
        Assertions.assertEquals(403, stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalCollection());
        Assertions.assertEquals(100, stationRepository.fetchStationSummary().get(StationType.AIRPORT).getTotalDiscount());
        Assertions.assertEquals(2, stationRepository.fetchStationSummary().get(StationType.AIRPORT).getPassengerTypeCount().get(PassengerType.ADULT));
        Assertions.assertEquals(2, stationRepository.fetchStationSummary().get(StationType.AIRPORT).getPassengerTypeCount().get(PassengerType.KID));
        metroService.printSummary();
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim().replace("\r",""));
    }

    @Test
    public void testCheckInAndPrintSummary2() {
        System.setOut(new PrintStream(outputStreamCaptor));
        String expectedOutput = "TOTAL_COLLECTION CENTRAL 457 50\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "ADULT 2\n" +
                "SENIOR_CITIZEN 1\n" +
                "TOTAL_COLLECTION AIRPORT 252 100\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "ADULT 1\n" +
                "KID 1\n" +
                "SENIOR_CITIZEN 1";
        metroService.balance("MC1", 400);
        metroService.balance("MC2", 100);
        metroService.balance("MC3", 50);
        metroService.balance("MC4", 50);
        metroService.checkIn("MC1", PassengerType.SENIOR_CITIZEN, StationType.AIRPORT);
        metroService.checkIn("MC2", PassengerType.KID, StationType.AIRPORT);
        metroService.checkIn("MC3", PassengerType.ADULT, StationType.CENTRAL);
        metroService.checkIn("MC1", PassengerType.SENIOR_CITIZEN, StationType.CENTRAL);
        metroService.checkIn("MC3", PassengerType.ADULT, StationType.AIRPORT);
        metroService.checkIn("MC3", PassengerType.ADULT, StationType.CENTRAL);
        metroService.printSummary();
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim().replace("\r",""));
    }
}
