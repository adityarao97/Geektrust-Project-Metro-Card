package com.geektrust.backend.commands;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.services.IMetroService;
import com.geektrust.backend.services.MetroServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BalanceCommandTest {

    @Test
    public void balanceCommandTest(){
        IMetroCardRepository metroCardRepository = new MetroCardRepository();
        IMetroService metroService = new MetroServiceImpl(metroCardRepository, new StationRepository());
        ICommand balanceCommand = new BalanceCommand(metroService);
        List<String> tokens = new ArrayList<>();
        tokens.add("BALANCE");
        tokens.add("MC1");
        tokens.add("300");
        balanceCommand.execute(tokens);
        Assert.assertTrue(new MetroCard("MC1", 300).equals(metroCardRepository.fetchMetroCardByID("MC1")));
    }
}
