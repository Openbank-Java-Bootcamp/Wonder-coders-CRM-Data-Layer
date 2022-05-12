package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;
import com.ironhack.WondercodersCRMDataLayer.Repository.SalesRepRepository;
import com.ironhack.WondercodersCRMDataLayer.Service.ContactService;
import com.ironhack.WondercodersCRMDataLayer.Service.LeadService;
import com.ironhack.WondercodersCRMDataLayer.Service.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class App {

    private boolean exit = false;
    private String currentCommand;
    private String currentId;

    @Autowired
    public SalesRepRepository salesRepRepository;
    @Autowired
    public SalesRepService salesRepService;
    @Autowired
    public ContactService contactService;
    @Autowired
    public LeadService leadService;



    public boolean isExit() {
        return exit;
    }
    public String getCurrentId() { return currentId; }
    public String getCurrentCommand() { return currentCommand; }

    public void initialize() {
        salesRepRepository.findAll();
        setAppCommands();
        showIntro();
        do {
            String nextCommand = setCurrentCommandAndId();
            executeCommand(nextCommand);
        } while (!isExit());
    }

    public void setAppCommands() {
        // COMMANDS:
        // APP
        Command commandList = new Command("command list", "command list", "print list of application's working commands", () -> Command.printCommandsTable(), false);
        Command reportList = new Command("report list", "report list", "print list of available reports", () -> Command.printReportsTable(), false);
        Command exit = new Command("exit", "exit", "quit CRM application", () -> exitApp(), false);
        // SALES REP
        Command newSalesRep = new Command("new sales rep", "new sales rep", "create new sales rep.", () -> salesRepService.addNewSalesRep(), false);
        Command findSalesRepById = new Command("lookup sales rep #", "lookup sales rep <IdNumber>", "print sales rep. with ID = <IdNumber>", () -> salesRepService.findSalesRepById(), false);
        Command showSalesReps = new Command("show sales reps", "show sales reps", "print list of all active sales rep.", () -> salesRepService.getAllSalesReps(), false);
        // CONTACTS
        Command findContact = new Command("lookup contact #", "lookup contact <IdNumber>", "print contact with ID = <IdNumber>", () -> contactService.lookUpContact(), false);
        Command showContacts = new Command("show contacts", "show contacts", "print list of all active contacts", () -> contactService.showContacts(), false);/*
        // ACCOUNTS
        Command findAccount = new Command("lookup account #", "lookup account <IdNumber>", "print account with ID = <IdNumber>", () -> Account.lookUpAccounts());
        Command showAccounts = new Command("show accounts", "show accounts", "print list of all active accounts", () -> Account.showAccounts());
        // OPPORTUNITIES
        Command closeWon = new Command("close-won #", "close-won <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = WON", () -> Opportunity.closeWon());
        Command closeLost = new Command("close-lost #", "close-lost <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = LOST", () -> Opportunity.closeLost());
        Command findOpportunity = new Command("lookup opportunity #", "lookup opportunity <IdNumber>", "print opportunity with ID = <IdNumber>", () -> Opportunity.lookUpOpportunity());
        Command showOpportunities = new Command("show opportunities", "show opportunities", "print list of all active opportunities", () -> Opportunity.showOpportunities());
        // LEADS*/
<<<<<<< HEAD
        Command removeLead = new Command("remove lead #", "remove lead <IdNumber>", "remove lead with ID = <IdNumber>", () -> leadService.removeLead(), false);
        //Command convertLead = new Command("convert #", "convert <IdNumber>", "convert lead with ID = <IdNumber> to new opportunity", () -> leadService.convertLead());
        Command findLead = new Command("lookup lead #", "lookup lead <IdNumber>", "print lead with ID = <IdNumber>", () -> leadService.lookUpLead(), false);
        Command showLeads = new Command("show leads", "show leads", "print list of all active leads", () -> leadService.showLeads(), false);
        Command newLead = new Command("new lead", "new lead", "create new lead", () -> leadService.newLead(), false);

        // REPORTS
        Command reportLeadBySalesRep = new Command("report lead by sales rep", "report lead by sales rep", "print list of all sales rep with their lead count", () -> leadService.reportLeadBySalesRep(), true);
=======
        Command removeLead = new Command("remove #", "remove <IdNumber>", "remove lead with ID = <IdNumber>", () -> leadService.removeLead());
        Command convertLead = new Command("convert #", "convert <IdNumber>", "convert lead with ID = <IdNumber> to new opportunity", () -> leadService.convertLead());
        Command findLead = new Command("lookup lead #", "lookup lead <IdNumber>", "print lead with ID = <IdNumber>", () -> leadService.lookUpLead());
        Command showLeads = new Command("show leads", "show leads", "print list of all active leads", () -> leadService.showLeads());
        Command newLead = new Command("new lead", "new lead", "create new lead", () -> leadService.newLead());
        Command reportLeadBySalesRep = new Command("report lead by sales rep", "report lead by sales rep", "print list of all sales rep with their lead count", () -> leadService.reportLeadBySalesRep());
>>>>>>> 2f9cc8024380e465a9cad06da88203661f0b5e82

    }

    public void showIntro() {
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

    public String setCurrentCommandAndId() {
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
        AppHelp.setId(currentId);
        return nextCommand;
    }

    public void executeCommand(String nextCommand) {
        // Execute command
        if(Command.getCommandsList().containsKey(currentCommand)) {
            Command.getCommandsList().get(currentCommand).execute();
        } else {
            System.err.println("No command matches '" + nextCommand + "' --> Type 'command list' to see the list of available commands.");
        }
    }

    public void exitApp() {
        System.out.println("Closing CRM app");
        exit = true;
    }

}
