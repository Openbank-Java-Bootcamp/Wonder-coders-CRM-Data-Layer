package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Enums.Industry;
import com.ironhack.WondercodersCRMDataLayer.Enums.Product;
import com.ironhack.WondercodersCRMDataLayer.Model.*;
import com.ironhack.WondercodersCRMDataLayer.Repository.*;
import com.ironhack.WondercodersCRMDataLayer.classes.App;
import com.ironhack.WondercodersCRMDataLayer.classes.AppHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactService contactService;
    App app = new App();
    AppHelp appHelp = new AppHelp();


    public void reportLeadBySalesRep() {
        List<Object[]> leads = leadRepository.reportLeadBySalesRepId();
        String title = "LEAD REPORT BY SALES REP";
        String[] headers = {"SALES REP NAME                                    ", "LEAD COUNT                                                                     "};
        List<String[]> list = new ArrayList<>();
        for (Object[] object : leads) {
            String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
            list.add(values);
        }
        appHelp.printTable(title, headers, list);
    }

    public void showLead(Lead newLead) {
        String title = "LEAD";
        String[] headers = {"ID      ", "NAME                      ", "PHONE NUMBER              ", "EMAIL                     ", "COMPANY NAME             "};
        List<String[]> list = new ArrayList<>();
        String[] values = {Integer.toString(newLead.getLeadId()), newLead.getName(), newLead.getPhoneNumber(), newLead.getEmail(), newLead.getCompanyName()};
        list.add(values);
        appHelp.printTable(title, headers, list);
    }

    /*public void newLead() {
        String name = appHelp.askForString("Name :");
        String phoneNumber = appHelp.askForString("Phone number: ");
        String email = appHelp.askForString("Email :");
        String companyName = appHelp.askForString("Company name :");
        int salesRepId = appHelp.askForInt("Sales Rep id :");
        SalesRep salesRep = new SalesRep();
        while (salesRepRepository.findById(salesRepId).isEmpty()) {
            System.err.println("No Sales Rep matches '" + salesRepId + "' --> Choose a Sales Rep from the following available ids list.");
            showSalesReps();
            salesRepId = AppHelp.askForInt("Sales Rep id :");
        }
        salesRep.setSalesRepId(salesRepId);
        salesRep = salesRepRepository.findById(salesRepId).get();
        Lead newLead = new Lead(name, phoneNumber, email, companyName, salesRep);
        leadRepository.save(newLead);
        System.out.println("New Lead created");
        showLead(newLead);
    }*/

    public void lookUpLead() {
        int id = Integer.parseInt(app.getCurrentId());
        if (leadRepository.findById(id).isPresent()) {
            showLead(leadRepository.findById(id).get());
        } else {
            System.err.println("No lead matches '" + id + "' --> Type 'show leads' to see the list of available ids.");
        }
    }

    public void showLeads() {
        List<Lead> leads = leadRepository.findAll();
        String title = "LEADS LIST";
        String[] headers = {"ID                                    ", "NAME                                                                     "};
        List<String[]> list = new ArrayList<>();
        for (Lead lead : leads) {
            String[] values = {Integer.toString(lead.getLeadId()), lead.getName()};
            list.add(values);
        }
        appHelp.printTable(title, headers, list);
    }

    /*public void convertLead() {
        int id = Integer.parseInt(app.getCurrentId());
        if (leadRepository.findById(id).isPresent()) {

            //Define all the parameters first.
            String name = leadRepository.findById(id).get().getName();
            String phoneNumber = leadRepository.findById(id).get().getPhoneNumber();
            String email = leadRepository.findById(id).get().getEmail();
            String companyName = leadRepository.findById(id).get().getCompanyName();
            Product[] productType = {Product.HYBRID, Product.FLATBED, Product.BOX};
            Industry[] industryType = {Industry.PRODUCE, Industry.ECOMMERCE, Industry.MANUFACTURING, Industry.MEDICAL, Industry.OTHER};
            SalesRep salesRep = leadRepository.findById(id).get().getSalesRepId();
            Account newAccount = new Account();

            //Create a new contact with the information of the lead.
            Contact decisionMaker = new Contact(name, phoneNumber, email);
            System.out.println("New contact created from Lead " + id);
            contactService.showContact(decisionMaker);
            ;

            //Create a new opportunity with the information we asked the user and the new contact created.
            System.out.println("Creating a new opportunity.");
            Product product = productType[appHelp.selectOption("Which product the customer is interested in?", productType)];
            int quantity = appHelp.askForInt("How many of them does the customer want?");
            Opportunity newOpportunity = new Opportunity(product, quantity, decisionMaker, salesRep);
            System.out.println("New opportunity created from Lead " + id);
            showOpportunity(newOpportunity);

            //Ask if a new Account needs to be created or not
            String answer = appHelp.askForYesOrNo("Would you like to create a new Account to associate with?");
            if (answer == "yes") {
                //Create a new account with the information we asked the user and the information of the lead.
                System.out.println("Creating a new account.");
                Industry industry = industryType[appHelp.selectOption("What industry is the company in?", industryType)];
                int employeeCount = appHelp.askForInt("How many employees are in the company?");
                String city = appHelp.askForString("What city is the company located in?");
                String country = appHelp.askForString("What country is the company from?");
                newAccount.setCompanyName(companyName);
                newAccount.setIndustry(industry);
                newAccount.setEmployeeCount(employeeCount);
                newAccount.setCity(city);
                newAccount.setCountry(country);
                System.out.println("New account created from Lead " + id);
                showAccount(newAccount);
            } else {
                //Ask for existing account id.
                int accountId = appHelp.askForInt("Existing Account id : ");
                while (accountRepository.findById(accountId).isEmpty()) {
                    System.err.println("No Account matches '" + accountId + "' --> Choose an Account from the following available ids list.");
                    showAccounts();
                    accountId = appHelp.askForInt("Existing Account id : ");
                }
                newAccount.setAccountId(accountId);
                newAccount = accountRepository.findById(accountId).get();
            }
            //Add the newly created decision maker and opportunity to the new accounts contacts list and opportunities list.
            newAccount.addContacts(decisionMaker);
            decisionMaker.setAccount(newAccount);
            newAccount.addOpportunities(newOpportunity);
            newOpportunity.setAccount(newAccount);

            //Save data in database.
            contactRepository.save(decisionMaker);
            opportunityRepository.save(newOpportunity);
            accountRepository.save(newAccount);


            //Remove the lead from the database once all the process is completed.
            removeLead();
        } else {
            System.err.println("No lead matches '" + id + "' --> Type 'show leads' to see the list of available ids.");
        }
    }*/

    public void removeLead() {
        int id = Integer.parseInt(app.getCurrentId());
        if (leadRepository.findById(id).isPresent()) {
            System.out.println("Lead deleted");
            showLead(leadRepository.findById(id).get());
            leadRepository.deleteById(id);
        } else {
            System.err.println("No lead matches '" + id + "' --> Type 'show leads' to see the list of available ids.");
        }
    }

}
