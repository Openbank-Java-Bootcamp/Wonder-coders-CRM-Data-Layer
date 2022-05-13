package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Enums.Status;
import com.ironhack.WondercodersCRMDataLayer.Model.Opportunity;
import com.ironhack.WondercodersCRMDataLayer.Repository.OpportunityRepository;
import com.ironhack.WondercodersCRMDataLayer.classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OpportunityService {
    @Autowired
    OpportunityRepository opportunityRepository;

    // Show details of an opportunity
    public void showOpportunity(Opportunity opportunity) {
        // Table title
        String title = "OPPORTUNITY";

        // Table headers
        String[] headers = {"PRODUCT         ", "QUANTITY    ", "DECISION MAKER                                       ", "STATUS            "};

        // List with the data to show
        List<String[]> list = new ArrayList<>();
        String[] arr = {opportunity.getProduct().toString(), Integer.toString(opportunity.getQuantity()), opportunity.getDecisionMaker().toString(), opportunity.getStatus().toString()};
        list.add(arr);

        AppHelp.printTable(title, headers, list);
    }

    // Show opportunity with id
    public void lookUpOpportunity() {
        int id = Integer.parseInt(AppHelp.getId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            // Table title
            String title = "THIS IS THE OPPORTUNITY WITH ID " + id;

            // Table headers
            String[] headers = {"ID                ", "PRODUCT         ", "QUANTITY    ", "DECISION MAKER                                       ", "STATUS            "};

            // List with the data to show
            List<String[]> list = new ArrayList<>();
            String[] arr = {opportunityFromDB.get().getOpportunityId().toString(), opportunityFromDB.get().getProduct().toString(), Integer.toString(opportunityFromDB.get().getQuantity()), opportunityFromDB.get().getDecisionMaker().toString(), opportunityFromDB.get().getStatus().toString()};
            list.add(arr);

            AppHelp.printTable(title, headers, list);
        } else {
            System.err.println("No opportunity matches '" + id + "' --> Type 'show opportunities' to see the list of available ids.");
        }
    }

    // Show list of opportunities
    public void showOpportunities() {
        List<Opportunity> opportunityList = opportunityRepository.findAll();

        if (opportunityList.isEmpty()) { // If the list is empty
            System.err.println("Opportunity list is empty");
        }

        // Table title
        String title = "OPPORTUNITY LIST";

        // Table headers
        String[] headers = {"ID                ", "PRODUCT         ", "QUANTITY    ", "DECISION MAKER                                       ", "ACCOUNT                 ", "STATUS            "};

        // List with the data to show
        List<String[]> list = new ArrayList<>();
        for (Opportunity opp : opportunityList) {
            String[] arr = {opp.getOpportunityId().toString(), opp.getProduct().toString(), Integer.toString(opp.getQuantity()), Integer.toString(opp.getDecisionMaker().getContactId()), Integer.toString(opp.getAccount().getAccountId()), opp.getStatus().toString()};
            list.add(arr);
        }

        AppHelp.printTable(title, headers, list);
    }

    // Change status to CLOSE_WON
    public void closeWon() {
        int id = Integer.parseInt(AppHelp.getId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            opportunityFromDB.get().setStatus(Status.CLOSED_WON);
            opportunityFromDB.get().setOpportunityId(id);
            opportunityRepository.save(opportunityFromDB.get());
            System.out.println("Status changed from OPEN to CLOSED_WON");
            showOpportunity(opportunityFromDB.get());
        } else {
            System.err.println("No opportunity matches '" + id + "' --> Type 'show opportunities' to see the list of available ids.");
        }
    }

    // Change status to CLOSE_LOSE
    public void closeLost() {
        int id = Integer.parseInt(AppHelp.getId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            opportunityFromDB.get().setStatus(Status.CLOSED_LOST);
            opportunityFromDB.get().setOpportunityId(id);
            opportunityRepository.save(opportunityFromDB.get());
            System.out.println("Status changed from OPEN to CLOSED_LOST");
            showOpportunity(opportunityFromDB.get());
        } else {
            System.err.println("No opportunity matches '" + id + "' --> Type 'show opportunities' to see the list of available ids.");
        }
    }

    // Count of all Opportunities by SalesRep
    public void reportOpportunitiesBySalesRep() {
        List<Object[]> opportunities = opportunityRepository.findAllBySalesRepId();
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY SALES REP";
            String[] headers = {"SALES REP NAME                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_WON status Opportunities by SalesRep
    public void reportOpportunitiesByStatusWonAndSalesRep() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndSalesRepId(Status.CLOSED_WON.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_WON AND SALES REP";
            String[] headers = {"SALES REP NAME                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_LOST status Opportunities by SalesRep
    public void reportOpportunitiesByStatusLostAndSalesRep() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndSalesRepId(Status.CLOSED_LOST.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_LOST AND SALES REP";
            String[] headers = {"SALES REP NAME                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all OPEN status Opportunities by SalesRep
    public void reportOpportunitiesByStatusOpenAndSalesRep() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndSalesRepId(Status.OPEN.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS OPEN AND SALES REP";
            String[] headers = {"SALES REP NAME                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all Opportunities by the Product
    public void reportOpportunitiesByProduct() {
        List<Object[]> opportunities = opportunityRepository.findAllByProduct();
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY PRODUCT";
            String[] headers = {"PRODUCT                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_WON status Opportunities by Product
    public void reportOpportunitiesByStatusWonAndProduct() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndProduct(Status.CLOSED_WON.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_WON AND PRODUCT";
            String[] headers = {"PRODUCT                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_LOST status Opportunities by Product
    public void reportOpportunitiesByStatusLostAndProduct() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndProduct(Status.CLOSED_LOST.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_LOST AND PRODUCT";
            String[] headers = {"PRODUCT                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all OPEN status Opportunities by Product
    public void reportOpportunitiesByStatusOpenAndProduct() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndProduct(Status.OPEN.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS OPEN AND PRODUCT";
            String[] headers = {"PRODUCT                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all Opportunities by the Country
    public void reportOpportunitiesByCountry() {
        List<Object[]> opportunities = opportunityRepository.findAllByCountry();
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY COUNTRY";
            String[] headers = {"COUNTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_WON status Opportunities by Country
    public void reportOpportunitiesByStatusWonAndCountry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCountry(Status.CLOSED_WON.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_WON AND COUNTRY";
            String[] headers = {"COUNTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_LOST status Opportunities by Country
    public void reportOpportunitiesByStatusLostAndCountry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCountry(Status.CLOSED_LOST.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_LOST AND COUNTRY";
            String[] headers = {"COUNTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all OPEN status Opportunities by Country
    public void reportOpportunitiesByStatusOpenAndCountry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCountry(Status.OPEN.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS OPEN AND COUNTRY";
            String[] headers = {"COUNTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all Opportunities by the City
    public void reportOpportunitiesByCity() {
        List<Object[]> opportunities = opportunityRepository.findAllByCity();
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY CITY";
            String[] headers = {"CITY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_WON status Opportunities by City
    public void reportOpportunitiesByStatusWonAndCity() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCity(Status.CLOSED_WON.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_WON AND CITY";
            String[] headers = {"CITY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_LOST status Opportunities by City
    public void reportOpportunitiesByStatusLostAndCity() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCity(Status.CLOSED_LOST.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_LOST AND CITY";
            String[] headers = {"CITY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all OPEN status Opportunities by City
    public void reportOpportunitiesByStatusOpenAndCity() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCountry(Status.OPEN.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS OPEN AND CITY";
            String[] headers = {"CITY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all Opportunities by the Industry
    public void reportOpportunitiesByIndustry() {
        List<Object[]> opportunities = opportunityRepository.findAllByIndustry();
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY INDUSTRY";
            String[] headers = {"INDUSTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_WON status Opportunities by Industry
    public void reportOpportunitiesByStatusWonAndIndustry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndIndustry(Status.CLOSED_WON.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_WON AND INDUSTRY";
            String[] headers = {"INDUSTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all CLOSE_LOST status Opportunities by Industry
    public void reportOpportunitiesByStatusLostAndIndustry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndIndustry(Status.CLOSED_LOST.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS CLOSED_LOST AND INDUSTRY";
            String[] headers = {"INDUSTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Count of all OPEN status Opportunities by Industry
    public void reportOpportunitiesByStatusOpenAndIndustry() {
        List<Object[]> opportunities = opportunityRepository.findAllByStatusAndCountry(Status.OPEN.toString());
        if (opportunities == null || opportunities.isEmpty()) {
            System.err.println("No info");
        } else {
            String title = "OPPORTUNITY REPORT BY STATUS OPEN AND INDUSTRY";
            String[] headers = {"INDUSTRY                                    ", "OPPORTUNITY COUNT                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Mean quantity of products order
    public void reportMeanQuantity() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            double meanQuantity = opportunityRepository.meanQuantity();
            String title = "MEAN QUANTITY REPORT OF PRODUCTS ORDER";
            String[] headers = {"MEAN QUANTITY                                    "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(meanQuantity)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    // Median quantity of products order
    public void reportMedianQuantity() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            double medianQuantity = opportunityRepository.medianQuantity();
            String title = "MEDIAN QUANTITY REPORT OF PRODUCTS ORDER";
            String[] headers = {"MEDIAN QUANTITY                                    "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(medianQuantity)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    // Maximum quantity of products order
    public void reportMaxQuantity() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            double maxQuantity = opportunityRepository.maxQuantity();
            String title = "MAXIMUM QUANTITY REPORT OF PRODUCTS ORDER";
            String[] headers = {"MAXIMUM QUANTITY                                    "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(maxQuantity)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    // Minimum quantity of products order
    public void reportMinQuantity() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            double minQuantity = opportunityRepository.minQuantity();
            String title = "MINIMUM QUANTITY REPORT OF PRODUCTS ORDER";
            String[] headers = {"MINIMUM QUANTITY                                    "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(minQuantity)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    // Mean number of Opportunities associated with an Account
    public void reportMeanOpportunities() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            List<Object[]> opportunities = opportunityRepository.meanOpportunities();
            String title = "MEAN NUMBER OF OPPORTUNITIES ASSOCIATED WITH AN ACCOUNT REPORT";
            String[] headers = {"ACCOUNT                                    ", "MEAN NUMBER                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Median number of Opportunities associated with an Account
    public void reportMedianOpportunities() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            List<Object[]> opportunities = opportunityRepository.medianOpportunities();
            String title = "MEDIAN NUMBER OF OPPORTUNITIES ASSOCIATED WITH AN ACCOUNT REPORT";
            String[] headers = {"ACCOUNT                                    ", "MEDIAN NUMBER                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Maximum number of Opportunities associated with an Account
    public void reportMaxOpportunities() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            List<Object[]> opportunities = opportunityRepository.maxOpportunities();
            String title = "MAXIMUM NUMBER OF OPPORTUNITIES ASSOCIATED WITH AN ACCOUNT REPORT";
            String[] headers = {"ACCOUNT                                    ", "MAXIMUM NUMBER                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }

    // Minimum number of Opportunities associated with an Account
    public void reportMinOpportunities() {
        if (opportunityRepository.findAll().isEmpty()) {
            System.err.println("No info");
        } else {
            List<Object[]> opportunities = opportunityRepository.minOpportunities();
            String title = "MINIMUM NUMBER OF OPPORTUNITIES ASSOCIATED WITH AN ACCOUNT REPORT";
            String[] headers = {"ACCOUNT                                    ", "MINIMUM NUMBER                                                                     "};
            List<String[]> list = new ArrayList<>();
            for (Object[] object : opportunities) {
                String[] values = {String.valueOf(object[0]), String.valueOf(object[1])};
                list.add(values);
            }
            AppHelp.printTable(title, headers, list);
        }
    }
}