package com.geektrust.backend.commands;

import com.geektrust.backend.entities.MetroCard;
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

import java.util.ArrayList;
import java.util.List;

public class CheckInCommandTest {

    @Test
    public void checkInCommandTest(){
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        IStationRepository stationRepository = new StationRepository();
        IMetroService metroService = new MetroServiceImpl(metroCardRepository, stationRepository);
        metroService.balance("MC1", 100);
        ICommand checkInCommand = new CheckInCommand(metroService);
        List<String> tokens = new ArrayList<>();
        tokens.add("CHECK_IN");
        tokens.add("MC1");
        tokens.add(PassengerType.KID.toString());
        tokens.add(StationType.AIRPORT.toString());
        checkInCommand.execute(tokens);
        Assert.assertTrue(stationRepository.fetchStationSummary().get(StationType.AIRPORT).getPassengerTypeCount().get(PassengerType.KID).equals(1));
    }
}
