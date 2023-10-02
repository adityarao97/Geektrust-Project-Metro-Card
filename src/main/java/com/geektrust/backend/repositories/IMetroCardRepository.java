package com.geektrust.backend.repositories;


import com.geektrust.backend.entities.MetroCard;

public interface IMetroCardRepository {
    public void save(String id, int amount);
    public MetroCard fetchMetroCardByID(String id);
}
