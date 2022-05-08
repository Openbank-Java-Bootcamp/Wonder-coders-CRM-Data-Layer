/*package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Model.Contact;
import com.ironhack.WondercodersCRMDataLayer.Model.Lead;
import com.ironhack.WondercodersCRMDataLayer.Model.Opportunity;
import com.ironhack.WondercodersCRMDataLayer.Repository.ContactRepository;
import com.ironhack.WondercodersCRMDataLayer.Repository.LeadRepository;
import com.ironhack.WondercodersCRMDataLayer.Repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void reportLeadBySalesRep(){
       List<Object[]> leads = leadRepository.reportLeadBySalesRep();
        String l1 = "%-2.2s";
        String s1 = "%-54.54s";
        String s2 = "%-54.54s";
        String l2 = "%2.2s";
        String format = l1 + " " + s1 + " " + s2 + " " + l2;
        System.out.print(TextColor.BLUE);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("| LEADS BY SALES REP                                                                                              |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, "| ","SALES REP NAME", "LEADS COUNT", " |");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for(Object[] object : leads) {
            System.out.format(format, "|", object[0], object[1], "|");
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print(TextColor.RESET);

    }

    public void showLead(Lead newLead) {
        String l1 = "%-2.2s";
        String s1 = "%-7.7s";
        String s2 = "%-25.25s";
        String s3 = "%-25.25s";
        String s4 = "%-25.25s";
        String s5 = "%-23.23s";
        String l2 = "%2.2s";
        String format = l1 + " " + s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5 + " " + l2;
        System.out.print(TextColor.BLUE);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("| LEAD                                                                                                            |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, "| ", "ID", "NAME", "PHONE NUMBER", "EMAIL", "COMPANY NAME", "|");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, "|", newLead.getLeadId(), newLead.getName(), newLead.getPhoneNumber(), newLead.getEmail(), newLead.getCompanyName(), "|");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print(TextColor.RESET);
    }

    public void newLead() {
        String name = AppHelp.askForString("Name :");
        String phoneNumber = AppHelp.askForString("Phone number: ");
        String email = AppHelp.askForString("Email :");
        String companyName = AppHelp.askForString("Company name :");
        int salesRepId = AppHelp.askForInt("Sales Rep id :");
        if (salesRepRepository.findBySalesRepId(salesRepId).isPresent()) {
            SalesRep salesRep = salesRepRepository.findBySalesRepId(salesRepId).get();
        } else {
            while (salesRepRepository.findBySalesRepId(salesRepId).isEmpty()) {
                System.err.println("No Sales Rep matches '" + salesRepId + "' --> Choose a Sales Rep from the following available ids list.");
                salesRepRepository.findAll();
                salesRepId = AppHelp.askForInt("Sales Rep id :");
            }
            SalesRep salesRep = salesRepRepository.findBySalesRepId(salesRepId).get();
        }
        Lead newLead = new Lead(name, phoneNumber, email, companyName, salesRep);
        leadRepository.save(newLead);
        System.out.println("New Lead created");
        showLead(newLead);
    }

    public void lookUpLead() {
        int id = Integer.parseInt(App.getCurrentId());
        if (leadRepository.findById(id).isPresent()) {
            Lead newLead = leadRepository.findById(id).get();
            showLead(newLead);
        } else {
            System.err.println("No lead matches '" + id + "' --> Type 'show leads' to see the list of available ids.");
        }
    }

    public void showLeads() {
        List<Lead> leads = leadRepository.findAll();
        String l1 = "%-2.2s";
        String s1 = "%-37.37s";
        String s2 = "%-71.71s";
        String l2 = "%2.2s";
        String format = l1 + " " + s1 + " " + s2 + " " + l2;
        System.out.print(TextColor.BLUE);
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("| LEADS LIST                                                                                                      |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, "| ","ID", "NAME", " |");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for(Lead lead : leads) {
            System.out.format(format, "|", lead.getLeadId(), lead.getName(), "|");
            System.out.println();
        };
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print(TextColor.RESET);

    }

    public void convertLead() {
        int id = Integer.parseInt(App.getCurrentId());
        if (leadRepository.findById(id).isPresent()) {

            //Define all the parameters first.
            String name = leadRepository.findById(id).get().getName();
            String phoneNumber = leadRepository.findById(id).get().getPhoneNumber();
            String email = leadRepository.findById(id).get().getEmail();
            String companyName = leadRepository.findById(id).get().getCompanyName();
            Product[] productType = {Product.HYBRID, Product.FLATBED, Product.BOX};
            Industry[] industryType = {Industry.PRODUCE, Industry.ECOMMERCE, Industry.MANUFACTURING, Industry.MEDICAL, Industry.OTHER};
            SalesRep salesRep = leadRepository.findById(id).get().getSalesRep();
            Account newAccount = new Account();

            //Create a new contact with the information of the lead.
            Contact decisionMaker = new Contact(name, phoneNumber, email);
            System.out.println("New contact created from Lead " + id);
            showContact(decisionMaker);
            ;

            //Create a new opportunity with the information we asked the user and the new contact created.
            System.out.println("Creating a new opportunity.");
            Product product = productType[AppHelp.selectOption("Which product the customer is interested in?", productType)];
            int quantity = AppHelp.askForInt("How many of them does the customer want?");
            Opportunity newOpportunity = new Opportunity(product, quantity, decisionMaker, salesRep);
            System.out.println("New opportunity created from Lead " + id);
            showOpportunity(newOpportunity);

            //Ask if a new Account needs to be created or not
            int answer = AppHelp.askForAnswer("Would you like to create a new Account to associate with?");
            if (answer == 1) {
                //Create a new account with the information we asked the user and the information of the lead.
                System.out.println("Creating a new account.");
                Industry industry = industryType[AppHelp.selectOption("What industry is the company in?", industryType)];
                int employeeCount = AppHelp.askForInt("How many employees are in the company?");
                String city = AppHelp.askForString("What city is the company located in?");
                String country = AppHelp.askForString("What country is the company from?");
                newAccount = new Account(companyName, industry, employeeCount, city, country);
                System.out.println("New account created from Lead " + id);
                showAccount(newAccount);
            } else {
                //Ask for existing account id.
                int accountId = AppHelp.askForInt("Existing Account id : ");
                if (accountpRepository.findByAccountId(accountId).isPresent()) {
                    newAccount = accountRepository.findByAccountId(accountId).get();
                    accountRepository.deleteById(accountId);
                } else {
                    while (accountRepository.finById(accountId).isEmpty()) {
                        System.err.println("No Account matches '" + accountId + "' --> Choose an Account from the following available ids list.");
                        accountRepository.findAll();
                        accountId = AppHelp.askForInt("Existing Account id : ");
                    }
                    newAccount = accountRepository.findByAccountId(accountId);
                    accountRepository.deleteById(accountId);
                }
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
            leadRepository.deleteById(id);
        } else {
            System.err.println("No lead matches '" + id + "' --> Type 'show leads' to see the list of available ids.");
        }
    }

}*/
