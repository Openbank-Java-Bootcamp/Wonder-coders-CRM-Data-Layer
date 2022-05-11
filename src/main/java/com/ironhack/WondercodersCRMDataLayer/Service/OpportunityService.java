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
    App app;
    Opportunity opportunity;

    @Autowired
    OpportunityRepository opportunityRepository;

    // Show details of an opportunity
    public void showOpportunity() {
        // Table title
        String title = "OPPORTUNITY";

        // Table headers
        String[] headers = {"ID                ", "PRODUCT         ", "QUANTITY    ", "DECISION MAKER                 ", "STATUS            "};

        // List with the data to show
        List<String[]> list = new ArrayList<>();
        String[] arr = {opportunity.getOpportunityId().toString(), opportunity.getProduct().toString(), Integer.toString(opportunity.getQuantity()), opportunity.getDecisionMaker().toString(), opportunity.getStatus().toString()};
        list.add(arr);

        AppHelp.printTable(title, headers, list);
    }

    // Show opportunity with id
    public void lookUpOpportunity() {
        int id = Integer.parseInt(app.getCurrentId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            // Table title
            String title = "THIS IS THE OPPORTUNITY WITH ID " + id;

            // Table headers
            String[] headers = {"ID                ", "PRODUCT         ", "QUANTITY    ", "DECISION MAKER                 ", "STATUS            "};

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
        String[] headers = {"ID                ", "PRODUCT         ", "QUANTITY    ", "DECISION MAKER                 ", "STATUS            "};

        // List with the data to show
        List<String[]> list = new ArrayList<>();
        for (Opportunity opp : opportunityList) {
            String[] arr = {opp.getOpportunityId().toString(), opp.getProduct().toString(), Integer.toString(opp.getQuantity()), opp.getDecisionMaker().toString(), opp.getStatus().toString()};
            list.add(arr);
        }

        AppHelp.printTable(title, headers, list);
    }

    // Change status to CLOSE_WON
    public void closeWon() {
        int id = Integer.parseInt(app.getCurrentId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            opportunityFromDB.get().setStatus(Status.CLOSED_WON);
        } else {
            System.err.println("No opportunity matches '" + id + "' --> Type 'show opportunities' to see the list of available ids.");
        }
    }

    // Change status to CLOSE_LOSE
    public void closeLost() {
        int id = Integer.parseInt(app.getCurrentId());
        // Get the opportunity from the database
        Optional<Opportunity> opportunityFromDB = opportunityRepository.findById(id);

        if (opportunityFromDB.isPresent()) { // If opportunity exists
            opportunityFromDB.get().setStatus(Status.CLOSED_LOST);
        } else {
            System.err.println("No opportunity matches '" + id + "' --> Type 'show opportunities' to see the list of available ids.");
        }
    }

    // Count of all Opportunities by SalesRep
    public List<Object[]> getOpportunitiesBySalesRep() {
        return opportunityRepository.findAllBySalesRepId();
    }

    // Count of all specific status Opportunities by SalesRep
    public List<Object[]> getOpportunitiesByStatusAndSalesRep(Status status) {
        return opportunityRepository.findAllByStatusAndSalesRepId(status);
    }

    // Count of all Opportunities by the product
    public List<Object[]> getOpportunitiesByProduct() {
        return opportunityRepository.findAllByProduct();
    }

    // Count of all specific status Opportunities by product
    public List<Object[]> getOpportunitiesByStatusAndProduct(Status status) {
        return opportunityRepository.findAllByStatusAndProduct(status);
    }

    // Count of all Opportunities by the country
    public List<Object[]> getOpportunitiesByCountry() {
        return opportunityRepository.findAllByCountry();
    }

    // Count of all specific status Opportunities by country
    public List<Object[]> getOpportunitiesByStatusAndCountry(Status status) {
        return opportunityRepository.findAllByStatusAndCountry(status);
    }

    // Count of all Opportunities by the city
    public List<Object[]> getOpportunitiesByCity() {
        return opportunityRepository.findAllByCity();
    }

    // Count of all specific status Opportunities by city
    public List<Object[]> getOpportunitiesByStatusAndCity(Status status) {
        return opportunityRepository.findAllByStatusAndCity(status);
    }

    // Count of all Opportunities by the industry
    public List<Object[]> getOpportunitiesByIndustry() {
        return opportunityRepository.findAllByIndustry();
    }

    // Count of all specific status Opportunities by industry
    public List<Object[]> getOpportunitiesByStatusAndIndustry(Status status) {
        return opportunityRepository.findAllByStatusAndIndustry(status);
    }

    // Mean quantity of products order
    public List<Object[]> getMeanQuantity() {
        return opportunityRepository.meanQuantity();
    }

    // Median quantity of products order
    public List<Object[]> getMedianQuantity() {
        return opportunityRepository.medianQuantity();
    }

    // Maximum quantity of products order
    public List<Object[]> getMaxQuantity() {
        return opportunityRepository.maxQuantity();
    }

    // Minimum quantity of products order
    public List<Object[]> getMinQuantity() {
        return opportunityRepository.minQuantity();
    }

    // Mean quantity of products order
    public List<Object[]> getMeanOpportunities() {
        return opportunityRepository.meanOpportunities();
    }

    // Median quantity of products order
    public List<Object[]> getMedianOpportunities() {
        return opportunityRepository.medianOpportunities();
    }

    // Maximum quantity of products order
    public List<Object[]> getMaxOpportunities() {
        return opportunityRepository.maxOpportunities();
    }

    // Minimum quantity of products order
    public List<Object[]> getMinOpportunities() {
        return opportunityRepository.minOpportunities();
    }
}
