package com.geektrust.backend.commands;

import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationType;
import com.geektrust.backend.services.IMetroService;
import com.geektrust.backend.services.MetroServiceImpl;

import java.util.List;

public class CheckInCommand implements ICommand {

    private final IMetroService metroService;

    public CheckInCommand(IMetroService metroService) {
        this.metroService = metroService;
    }

    @Override
    public void execute(List<String> tokens) {
        String metroCardID = tokens.get(1);
        PassengerType passengerType = PassengerType.valueOf(tokens.get(2));
        StationType stationType = StationType.valueOf(tokens.get(3));
        metroService.checkIn(metroCardID, passengerType, stationType);
    }
}
