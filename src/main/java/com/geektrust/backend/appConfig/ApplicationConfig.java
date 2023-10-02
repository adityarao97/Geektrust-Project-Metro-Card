package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.BalanceCommand;
import com.geektrust.backend.commands.CheckInCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.services.IMetroService;
import com.geektrust.backend.services.MetroServiceImpl;

public class ApplicationConfig {
    private final IMetroCardRepository metroCardRepository = new MetroCardRepository();
    private final IStationRepository stationRepository = new StationRepository();
    private final IMetroService metroService = new MetroServiceImpl(metroCardRepository, stationRepository);
    private final BalanceCommand balanceCommand = new BalanceCommand(metroService);
    private final CheckInCommand checkInCommand = new CheckInCommand(metroService);
    private final PrintSummaryCommand printSummaryCommand = new PrintSummaryCommand(metroService);
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("BALANCE", balanceCommand);
        commandInvoker.register("CHECK_IN", checkInCommand);
        commandInvoker.register("PRINT_SUMMARY", printSummaryCommand);
        return commandInvoker;
    }
}