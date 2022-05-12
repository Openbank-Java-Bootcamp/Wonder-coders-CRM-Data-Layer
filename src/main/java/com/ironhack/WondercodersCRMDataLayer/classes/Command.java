package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command {

    private static Map<String, Command> commandsList = new HashMap<>();
    private static Map<String, Command> reportsList = new HashMap<>();

    private final String commandText;
    private final String commandSampleText;
    private final String description;
    private final Runnable method;
    private boolean isReport;

    public Command(String commandText, String commandSampleText, String description, Runnable method, boolean isReport) {
        this.commandText = commandText;
        this.commandSampleText = commandSampleText;
        this.description = description;
        this.method = method;
        if(isReport) {
            reportsList.put(commandText, this);
        } else {
            commandsList.put(commandText, this);
        }
    }

    public static Map<String, Command> getCommandsList() { return commandsList; }
    public static Map<String, Command> getReportsList() {
        return reportsList;
    }
    public String getCommandSampleText() { return commandSampleText; }
    public String getDescription() { return description; }

    public void execute() {
        this.method.run();
    }

    public static void printCommandsTable() {
        // Title for commands table:
        String title = "COMMAND LIST";
        // Headers set the size of each column:
        String[] headers = {"COMMAND                            ", "       ", "DESCRIPTION                                          "};
        // Convert map to List<String[]>
        List<String[]> list = new ArrayList<>();
        commandsList.forEach((key, value) -> {
            String[] arr = {value.getCommandSampleText(), " --> ", value.getDescription()};
            list.add(arr);
        });
        // Execute printTable method:
        AppHelp.printTable(title, headers, list );
    }
    public static void printReportsTable() {
        // Title for commands table:
        String title = "REPORTS LIST";
        // Headers set the size of each column:
        String[] headers = {"COMMAND                            ", "       ", "DESCRIPTION                                          "};
        // Convert map to List<String[]>
        List<String[]> list = new ArrayList<>();
        reportsList.forEach((key, value) -> {
            String[] arr = {value.getCommandSampleText(), " --> ", value.getDescription()};
            list.add(arr);
        });
        // Execute printTable method:
        AppHelp.printTable(title, headers, list );
    }

}
