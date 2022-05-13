package com.ironhack.WondercodersCRMDataLayer.classes;

import com.ironhack.WondercodersCRMDataLayer.Enums.Color;
import com.ironhack.WondercodersCRMDataLayer.Enums.Industry;
import com.ironhack.WondercodersCRMDataLayer.Enums.Product;
import com.ironhack.WondercodersCRMDataLayer.Enums.Status;
import com.ironhack.WondercodersCRMDataLayer.Model.*;
import com.ironhack.WondercodersCRMDataLayer.Repository.*;
import com.ironhack.WondercodersCRMDataLayer.Service.*;
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
    public LeadRepository leadRepository;
    @Autowired
    public OpportunityRepository opportunityRepository;
    @Autowired
    public ContactRepository contactRepository;
    @Autowired
    public AccountRepository accountRepository;


    @Autowired
    public SalesRepService salesRepService;
    @Autowired
    public ContactService contactService;
    @Autowired
    public LeadService leadService;
    @Autowired
    public AccountService accountService;
    @Autowired
    public OpportunityService opportunityService;



    public boolean isExit() {
        return exit;
    }
    public String getCurrentId() { return currentId; }
    public String getCurrentCommand() { return currentCommand; }

    public void initialize() {
        salesRepRepository.findAll();
        setAppCommands();
        setMinData();
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
        // LEADS
        Command newLead = new Command("new lead", "new lead", "create new lead", () -> leadService.newLead(), false);
        Command findLead = new Command("lookup lead #", "lookup lead <IdNumber>", "print lead with ID = <IdNumber>", () -> leadService.lookUpLead(), false);
        Command showLeads = new Command("show leads", "show leads", "print list of all active leads", () -> leadService.showLeads(), false);
        Command removeLead = new Command("remove lead #", "remove lead <IdNumber>", "remove lead with ID = <IdNumber>", () -> leadService.removeLead(), false);
        Command convertLead = new Command("convert #", "convert <IdNumber>", "convert lead with ID = <IdNumber> to new opportunity", () -> leadService.convertLead(),false);
        // CONTACTS
        Command findContact = new Command("lookup contact #", "lookup contact <IdNumber>", "print contact with ID = <IdNumber>", () -> contactService.lookUpContact(), false);
        Command showContacts = new Command("show contacts", "show contacts", "print list of all active contacts", () -> contactService.showContacts(), false);
        // ACCOUNTS
        Command findAccount = new Command("lookup account #", "lookup account <IdNumber>", "print account with ID = <IdNumber>", () -> accountService.lookUpAccount(),false);
        Command showAccounts = new Command("show accounts", "show accounts", "print list of all active accounts", () -> accountService.showAccounts(),false);
        // OPPORTUNITIES
        Command findOpportunity = new Command("lookup opportunity #", "lookup opportunity <IdNumber>", "print opportunity with ID = <IdNumber>", () -> opportunityService.lookUpOpportunity(),false);
        Command showOpportunities = new Command("show opportunities", "show opportunities", "print list of all active opportunities", () -> opportunityService.showOpportunities(),false);
        Command closeWon = new Command("close-won #", "close-won <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = WON", () -> opportunityService.closeWon(),false);
        Command closeLost = new Command("close-lost #", "close-lost <IdNumber>", "Closing opportunity with ID = <IdNumber> with status = LOST", () -> opportunityService.closeLost(),false);

        // REPORTS:
        // By SalesRep
        Command reportLeadBySalesRep = new Command("report lead by sales rep", "report lead by sales rep", "Display a count of Leads by SalesRep", () -> leadService.reportLeadBySalesRep(), true);
        Command reportOpportunityBySalesRep = new Command("report opportunity by salesrep", "report opportunity by salesrep", "Display a count of all Opportunities by SalesRep", () -> opportunityService.reportOpportunitiesBySalesRep(), true);
        Command reportClosedWonBySalesRep = new Command("report closed-won by salesrep", "report closed-won by salesrep", "Display a count of all CLOSED_WON Opportunities by SalesRep", () -> opportunityService.reportOpportunitiesByStatusWonAndSalesRep(), true);
        Command reportClosedLostBySalesRep = new Command("report closed-lost by salesrep", "report closed-lost by salesrep", "Display a count of all CLOSED_LOST Opportunities by SalesRep", () -> opportunityService.reportOpportunitiesByStatusLostAndSalesRep(), true);
        Command reportOpenBySalesRep = new Command("report open by salesrep", "report open by salesrep", "Display a count of all OPEN Opportunities by SalesRep", () -> opportunityService.reportOpportunitiesByStatusOpenAndSalesRep(), true);
        // By Product
        Command reportOpportunityByProduct = new Command("report opportunity by the product", "report opportunity by the product", "Display a count of all Opportunities by the product", () -> opportunityService.reportOpportunitiesByProduct(), true);
        Command reportClosedWonByProduct = new Command("report closed-won by the product", "report closed-won by the product", "Display a count of all CLOSED_WON Opportunities by the product", () -> opportunityService.reportOpportunitiesByStatusWonAndProduct(), true);
        Command reportClosedLostByProduct = new Command("report closed-lost by the product", "report closed-lost by the product", "Display a count of all CLOSED_LOST Opportunities by the product", () -> opportunityService.reportOpportunitiesByStatusLostAndProduct(), true);
        Command reportOpenByProduct = new Command("report open by the product", "report open by the product", "Display a count of all OPEN Opportunities by the product", () -> opportunityService.reportOpportunitiesByStatusOpenAndProduct(), true);
        // By Country
        Command reportOpportunityByCountry = new Command("report opportunity by country", "report opportunity by country", "Display a count of all Opportunities by country", () -> opportunityService.reportOpportunitiesByCountry(), true);
        Command reportClosedWonByCountry = new Command("report closed-won by country", "report closed-won by country", "Display a count of all CLOSED_WON Opportunities by country", () -> opportunityService.reportOpportunitiesByStatusWonAndCountry(), true);
        Command reportClosedLostByCountry = new Command("report closed-lost by country", "report closed-lost by country", "Display a count of all CLOSED_LOST Opportunities by country", () -> opportunityService.reportOpportunitiesByStatusLostAndCountry(), true);
        Command reportOpenByCountry = new Command("report open by country", "report open by country", "Display a count of all OPEN Opportunities by country", () -> opportunityService.reportOpportunitiesByStatusOpenAndCountry(), true);
        // By City
        Command reportOpportunityByCity = new Command("report opportunity by city", "report opportunity by city", "Display a count of all Opportunities by city", () -> opportunityService.reportOpportunitiesByCity(), true);
        Command reportClosedWonByCity = new Command("report closed-won by city", "report closed-won by city", "Display a count of all CLOSED_WON Opportunities by city", () -> opportunityService.reportOpportunitiesByStatusWonAndCity(), true);
        Command reportClosedLostByCity = new Command("report closed-lost by city", "report closed-lost by city", "Display a count of all CLOSED_LOST Opportunities by city", () -> opportunityService.reportOpportunitiesByStatusLostAndCity(), true);
        Command reportOpenByCity = new Command("report open by city", "report open by city", "Display a count of all OPEN Opportunities by city", () -> opportunityService.reportOpportunitiesByStatusOpenAndCity(), true);
        // By Industry
        Command reportOpportunityByIndustry = new Command("report opportunity by industry", "report opportunity by industry", "Display a count of all Opportunities by Industry", () -> opportunityService.reportOpportunitiesByIndustry(), true);
        Command reportClosedWonByIndustry = new Command("report closed-won by industry", "report closed-won by industry", "Display a count of all CLOSED_WON Opportunities by Industry", () -> opportunityService.reportOpportunitiesByStatusWonAndIndustry(), true);
        Command reportClosedLostByIndustry = new Command("report closed-lost by industry", "report closed-lost by industry", "Display a count of all CLOSED_LOST Opportunities by Industry", () -> opportunityService.reportOpportunitiesByStatusLostAndIndustry(), true);
        Command reportOpenByIndustry = new Command("report open by industry", "report open by industry", "Display a count of all OPEN Opportunities by Industry", () -> opportunityService.reportOpportunitiesByStatusOpenAndIndustry(), true);
        // EmployeeCount States
        Command reportMeanEmployeeCount = new Command("mean employee count", "mean employee count", "Display the mean employee count", () -> accountService.getMeanEmployees(), true);
        Command reportMedianEmployeeCount = new Command("median employee count", "median employee count", "Display the median employee count", () -> accountService.getMedianEmployees(), true);
        Command reportMaxEmployeeCount = new Command("max employee count", "max employee count", "Display the max employee count", () -> accountService.getMaxEmployeeCount(), true);
        Command reportMinEmployeeCount = new Command("min employee count", "min employee count", "Display the min employee count", () -> accountService.getMinEmployeeCount(), true);
        // Quantity States
        Command reportMeanProdQuantity = new Command("mean quantity", "mean quantity", "Display the mean quantity of products order", () -> opportunityService.reportMeanQuantity(), true);
        Command reportMedianProdQuantity = new Command("median quantity", "median quantity", "Display the median quantity of products order", () -> opportunityService.reportMedianQuantity(), true);
        Command reportMaxProdQuantity = new Command("max quantity", "max quantity", "Display the max quantity of products order", () -> opportunityService.reportMaxQuantity(), true);
        Command reportMinProdQuantity = new Command("min quantity", "min quantity", "Display the min quantity of products order", () -> opportunityService.reportMinQuantity(), true);
        // Opportunity States
        Command reportMeanAccOpportunity = new Command("mean opps per account", "mean opps per account", "Display the mean number of Opportunities associated with an Account", () -> opportunityService.reportMeanOpportunities(), true);
        Command reportMedianAccOpportunity = new Command("median opps per account", "median opps per account", "Display the median number of Opportunities associated with an Account", () -> opportunityService.reportMedianOpportunities(), true);
        Command reportMaxAccOpportunity = new Command("max opps per account", "max opps per account", "Display the max number of Opportunities associated with an Account", () -> opportunityService.reportMaxOpportunities(), true);
        Command reportMinAccOpportunity = new Command("min opps per account", "min opps per account", "Display the min number of Opportunities associated with an Account", () -> opportunityService.reportMinOpportunities(), true);
    }

    public void setMinData() {

        // Sales reps
        salesRepRepository.save(new SalesRep("Ana"));
        salesRepRepository.save(new SalesRep("Juan"));
        salesRepRepository.save(new SalesRep("Soledad"));

        //Leads
        leadRepository.save(new Lead("Nuria", "111111111", "nuri@nuria.com", "Nuria&Co", salesRepRepository.findByName("Ana")));
        leadRepository.save(new Lead("Olatz", "222222222", "olatz@olatz.com", "Olatz&Co", salesRepRepository.findByName("Juan")));
        leadRepository.save(new Lead("Paula", "333333333", "paula@paula.com", "Paula&Co", salesRepRepository.findByName("Ana")));

        // Accounts
        accountRepository.save(new Account("Beatriz&Co", Industry.ECOMMERCE, 3, "Madrid", "Spain"));
        accountRepository.save(new Account("Saul&Co", Industry.MANUFACTURING, 7, "Bilbao", "Spain"));
        accountRepository.save(new Account("Fer&Co", Industry.PRODUCE, 5, "Barcelona", "Spain"));

        // Contacts
        Contact c1 = new Contact("Beatriz", "444444444", "beatriz@beatriz.com");
        c1.setAccount(accountRepository.findByCompanyName("Beatriz&Co"));
        contactRepository.save(c1);

        Contact c2 = new Contact("Saul", "555555555", "saul@saul.com");
        c2.setAccount(accountRepository.findByCompanyName("Saul&Co"));
        contactRepository.save(c2);

        Contact c3 = new Contact("Fer", "666666666", "fer@fer.com");
        c3.setAccount(accountRepository.findByCompanyName("Fer&Co"));
        contactRepository.save(c3);

        // Opportunities
        Opportunity o1 = new Opportunity(Product.FLATBED, 3, contactRepository.findByName("Beatriz"), salesRepRepository.findByName("Ana"));
        o1.setStatus(Status.CLOSED_WON);
        o1.setAccount(accountRepository.findByCompanyName("Beatriz&Co"));
        opportunityRepository.save(o1);

        Opportunity o2 = new Opportunity(Product.HYBRID, 2, contactRepository.findByName("Saul"), salesRepRepository.findByName("Soledad"));
        o2.setStatus(Status.CLOSED_LOST);
        o2.setAccount(accountRepository.findByCompanyName("Saul&Co"));
        opportunityRepository.save(o2);

        Opportunity o3 = new Opportunity(Product.BOX, 7, contactRepository.findByName("Fer"), salesRepRepository.findByName("Juan"));
        o3.setAccount(accountRepository.findByCompanyName("Fer&Co"));
        opportunityRepository.save(o3);

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
        } else if(Command.getReportsList().containsKey(currentCommand)) {
            Command.getReportsList().get(currentCommand).execute();
        } else {
            System.err.println("No command matches '" + nextCommand + "' --> Type 'command list' to see the list of available commands.");
        }
    }

    public void exitApp() {
        System.out.println("Closing CRM app");
        exit = true;
    }

}
