package com.geektrust.backend;

import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class App {

	public static void main(String[] args) {
		run(args[0]);
	}
	public static void run(String args) {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		BufferedReader reader;
        try {
			reader = new BufferedReader(new FileReader(args));
			String line = reader.readLine();
			while (line != null) {
				List<String> tokens = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(tokens.get(0),tokens);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException | NoSuchCommandException e) {
			e.printStackTrace();
		}
	}
}
