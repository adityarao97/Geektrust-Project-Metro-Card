package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.DuplicateMetroCardException;
import com.geektrust.backend.exceptions.MetroCardDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

public class MetroCardRepository implements IMetroCardRepository{

    Map<String, MetroCard> metroCardMap;

    public MetroCardRepository(){
        metroCardMap = new HashMap<>();
    }

    @Override
    public void save(String id, int balance) {
        if(!metroCardMap.containsKey(id)){
            MetroCard metroCard = new MetroCard(id, balance);
            metroCardMap.put(id, metroCard);
        }
        else{
            throw new DuplicateMetroCardException("Metro card with id : " + id + " already exists");
        }
    }

    @Override
    public MetroCard fetchMetroCardByID(String id) {
        if(metroCardMap.containsKey(id)){
            return metroCardMap.get(id);
        }
        else{
            throw new MetroCardDoesNotExistException("Metro card doesn't exist with id : " + id);
        }
    }
}
