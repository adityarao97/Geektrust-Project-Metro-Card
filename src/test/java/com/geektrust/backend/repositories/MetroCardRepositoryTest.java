package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.DuplicateMetroCardException;
import com.geektrust.backend.exceptions.MetroCardDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetroCardRepositoryTest {

    @Test
    public void saveAndFetchTest(){
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        String id = "1";
        int balance = 200;
        metroCardRepository.save(id, balance);
        Assertions.assertTrue(metroCardRepository.fetchMetroCardByID(id).equals(new MetroCard(id, balance)));
    }

    @Test
    public void saveAndExceptionTest(){
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        String id = "1";
        int balance = 200;
        metroCardRepository.save(id, balance);
        Assertions.assertThrows(DuplicateMetroCardException.class, ()-> metroCardRepository.save(id,balance));
    }

    @Test
    public void fetchAndExceptionTest(){
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        String id = "1";
        int balance = 200;
        metroCardRepository.save(id, balance);
        Assertions.assertThrows(MetroCardDoesNotExistException.class, ()-> metroCardRepository.fetchMetroCardByID("2"));
    }
}
