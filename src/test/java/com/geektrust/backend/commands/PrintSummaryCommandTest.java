package com.geektrust.backend.commands;

import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationType;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.services.IMetroService;
import com.geektrust.backend.services.MetroServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PrintSummaryCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void printSummaryCommandTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        String expectedOutput = "TOTAL_COLLECTION CENTRAL 0 0\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "TOTAL_COLLECTION AIRPORT 50 0\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "KID 1";
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        IStationRepository stationRepository = new StationRepository();
        IMetroService metroService = new MetroServiceImpl(metroCardRepository, stationRepository);
        metroService.balance("MC1", 100);
        metroService.checkIn("MC1", PassengerType.KID, StationType.AIRPORT);
        ICommand printSummaryCommand = new PrintSummaryCommand(metroService);
        List<String> tokens = new ArrayList<>();
        tokens.add("PRINT_SUMMARY");
        printSummaryCommand.execute(tokens);
        Assert.assertEquals(expectedOutput, outputStreamCaptor.toString().trim().replace("\r",""));
    }
}
