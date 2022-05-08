package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;

public class App {

    private static boolean exit = false;
    private static String currentCommand;
    private static String currentId;

    public static boolean isExit() {
        return exit;
    }
    public static String getCurrentId() { return currentId; }
    public static String getCurrentCommand() { return currentCommand; }

    public static void initialize() {
        setAppCommands();
        showIntro();
        do {
            String nextCommand = setCurrentCommandAndId();
            executeCommand(nextCommand);
        } while (!isExit());
    }

    public static void setAppCommands() {
        // COMMANDS:
        // APP
        Command commandList = new Command("command list", "command list", "print list of application's working commands", () -> Command.printCommandsTable());
        Command exit = new Command("exit", "exit", "quit CRM application", () -> exitApp());

        /*
        // CONTACTS
        Command findContact = new Command("lookup contact #", "lookup contact <IdNumber>", "print contact with ID = <IdNumber>", () -> Contact.lookUpContact());
        Command showContacts = new Command("show contacts", "show contacts", "print list of all active contacts", () -> Contact.showContacts());
        // ACCOUNTS
        Command findAccount = new Command("lookup account #", "lookup account <IdNumber>", "print account with ID = <IdNumber>", () -> Account.lookUpAccounts());
        Command showAccounts = new Command("show accounts", "show accounts", "print list of all active accounts", () -> Account.showAccounts());
        // OPPORTUNITIES
        Command closeWon = new Command("close-won #", "close-won <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = WON", () -> Opportunity.closeWon());
        Command closeLost = new Command("close-lost #", "close-lost <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = LOST", () -> Opportunity.closeLost());
        Command findOpportunity = new Command("lookup opportunity #", "lookup opportunity <IdNumber>", "print opportunity with ID = <IdNumber>", () -> Opportunity.lookUpOpportunity());
        Command showOpportunities = new Command("show opportunities", "show opportunities", "print list of all active opportunities", () -> Opportunity.showOpportunities());
        // LEADS
        Command removeLead = new Command("remove #", "remove <IdNumber>", "remove lead with ID = <IdNumber>", () -> Lead.removeLead());
        Command convertLead = new Command("convert #", "convert <IdNumber>", "convert lead with ID = <IdNumber> to new opportunity", () -> Lead.convertLead());
        Command findLead = new Command("lookup lead #", "lookup lead <IdNumber>", "print lead with ID = <IdNumber>", () -> Lead.lookUpLead());
        Command showLeads = new Command("show leads", "show leads", "print list of all active leads", () -> Lead.showLeads());
        Command newLead = new Command("new lead", "new lead", "create new lead", () -> Lead.newLead());
        */
    }

    public static void showIntro() {
        TextColor.changeTo(Color.WHITE_BOLD_BRIGHT);
        System.out.println(
                // Text block
                """

                -------------------------------------------------------------------------------------------------------------------
                                                                welcome
                                                                  to
                                                         ·······················
                                                    ···········WONDER CRM···········
                                                         ·······················
                                                                  by
                                                             wonder-coders
                -------------------------------------------------------------------------------------------------------------------
                """
        );
        TextColor.reset();
        System.out.println("These are all available commands:");
        Command.printCommandsTable();
    }

    public static String setCurrentCommandAndId() {
        currentCommand = null;
        currentId = null;

        // Get command from user (case-insensitive)
        TextColor.changeTo(Color.GREEN);
        String nextCommand = AppHelp.askForString("\nEnter next command:").toLowerCase().replaceAll("#", "-");
        TextColor.reset();

        // Get generic command and ID in case user's command contains one
        String[] commandWords = nextCommand.split(" ");
        if( commandWords[commandWords.length - 1].replaceAll("[0-9]", "").equals("") ) {
            currentId = commandWords[commandWords.length - 1];
            commandWords[commandWords.length - 1] = "#";
        }
        currentCommand = String.join(" ", commandWords);

        return nextCommand;
    }

    public static void executeCommand(String nextCommand) {
        // Execute command
        if(Command.getCommandsList().containsKey(currentCommand)) {
            Command.getCommandsList().get(currentCommand).execute();
        } else {
            System.err.println("No command matches '" + nextCommand + "' --> Type 'command list' to see the list of available commands.");
        }
    }

    public static void exitApp() {
        System.out.println("Closing CRM app");
        exit = true;
    }

}