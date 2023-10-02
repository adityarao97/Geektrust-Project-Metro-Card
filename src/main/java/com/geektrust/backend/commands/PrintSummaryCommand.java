package com.geektrust.backend.commands;

import com.geektrust.backend.services.IMetroService;

import java.util.List;

public class PrintSummaryCommand implements ICommand{

    private final IMetroService metroService;

    public PrintSummaryCommand(IMetroService metroService){
        this.metroService = metroService;
    }
    @Override
    public void execute(List<String> tokens) {
        metroService.printSummary();
    }
}