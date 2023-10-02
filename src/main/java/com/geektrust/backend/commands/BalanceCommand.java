package com.geektrust.backend.commands;

import com.geektrust.backend.services.IMetroService;

import java.util.List;

public class BalanceCommand implements ICommand {

    private final IMetroService metroService;

    public BalanceCommand(IMetroService metroService) {
        this.metroService = metroService;
    }

    @Override
    public void execute(List<String> tokens) {
        String id = tokens.get(1);
        int amount = Integer.parseInt(tokens.get(2));
        metroService.balance(id, amount);
    }
}
