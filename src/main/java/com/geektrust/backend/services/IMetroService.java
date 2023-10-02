package com.geektrust.backend.services;

import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationType;

public interface IMetroService {
    public void balance(String id, int amount);
    public void checkIn(String id, PassengerType passengerType, StationType stationType);
    public void printSummary();
}