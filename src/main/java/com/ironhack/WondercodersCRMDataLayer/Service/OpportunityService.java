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
<<<<<<< HEAD
=======
    /*@Autowired
    App app;*/

    App app = new App();

>>>>>>> 789df79349876f74364bc8759546530108c8e3b2
    @Autowired
    OpportunityRepository opportunityRepository;

    // Show details of an opportunity
    public void showOpportunity(Opportunity opportunity) {
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
        int id = Integer.parseInt(AppHelp.getId());
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
        int id = Integer.parseInt(AppHelp.getId());
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
        int id = Integer.parseInt(AppHelp.getId());
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

    // Count of all CLOSE_WON status Opportunities by SalesRep
    public List<Object[]> getOpportunitiesByStatusWonAndSalesRep() {
        return opportunityRepository.findAllByStatusAndSalesRepId(Status.CLOSED_WON);
    }

    // Count of all CLOSE_LOST status Opportunities by SalesRep
    public List<Object[]> getOpportunitiesByStatusLostAndSalesRep() {
        return opportunityRepository.findAllByStatusAndSalesRepId(Status.CLOSED_LOST);
    }

    // Count of all OPEN status Opportunities by SalesRep
    public List<Object[]> getOpportunitiesByStatusOpenAndSalesRep() {
        return opportunityRepository.findAllByStatusAndSalesRepId(Status.OPEN);
    }

    // Count of all Opportunities by the Product
    public List<Object[]> getOpportunitiesByProduct() {
        return opportunityRepository.findAllByProduct();
    }

    // Count of all CLOSE_WON status Opportunities by Product
    public List<Object[]> getOpportunitiesByStatusWonAndProduct() {
        return opportunityRepository.findAllByStatusAndProduct(Status.CLOSED_WON);
    }

    // Count of all CLOSE_LOST status Opportunities by Product
    public List<Object[]> getOpportunitiesByStatusLostAndProduct() {
        return opportunityRepository.findAllByStatusAndProduct(Status.CLOSED_LOST);
    }

    // Count of all OPEN status Opportunities by Product
    public List<Object[]> getOpportunitiesByStatusOpenAndProduct() {
        return opportunityRepository.findAllByStatusAndProduct(Status.OPEN);
    }

    // Count of all Opportunities by the Country
    public List<Object[]> getOpportunitiesByCountry() {
        return opportunityRepository.findAllByCountry();
    }

    // Count of all CLOSE_WON status Opportunities by Country
    public List<Object[]> getOpportunitiesByStatusWonAndCountry() {
        return opportunityRepository.findAllByStatusAndCountry(Status.CLOSED_WON);
    }

    // Count of all CLOSE_LOST status Opportunities by Country
    public List<Object[]> getOpportunitiesByStatusLostAndCountry() {
        return opportunityRepository.findAllByStatusAndCountry(Status.CLOSED_LOST);
    }

    // Count of all OPEN status Opportunities by Country
    public List<Object[]> getOpportunitiesByStatusOpenAndCountry() {
        return opportunityRepository.findAllByStatusAndCountry(Status.OPEN);
    }

    // Count of all Opportunities by the City
    public List<Object[]> getOpportunitiesByCity() {
        return opportunityRepository.findAllByCity();
    }

    // Count of all CLOSE_WON status Opportunities by City
    public List<Object[]> getOpportunitiesByStatusWonAndCity() {
        return opportunityRepository.findAllByStatusAndCity(Status.CLOSED_WON);
    }

    // Count of all CLOSE_LOST status Opportunities by City
    public List<Object[]> getOpportunitiesByStatusLostAndCity() {
        return opportunityRepository.findAllByStatusAndCity(Status.CLOSED_LOST);
    }

    // Count of all OPEN status Opportunities by City
    public List<Object[]> getOpportunitiesByStatusOpenAndCity() {
        return opportunityRepository.findAllByStatusAndCountry(Status.OPEN);
    }

    // Count of all Opportunities by the Industry
    public List<Object[]> getOpportunitiesByIndustry() {
        return opportunityRepository.findAllByIndustry();
    }

    // Count of all CLOSE_WON status Opportunities by Industry
    public List<Object[]> getOpportunitiesByStatusWonAndIndustry() {
        return opportunityRepository.findAllByStatusAndIndustry(Status.CLOSED_WON);
    }

    // Count of all CLOSE_LOST status Opportunities by Industry
    public List<Object[]> getOpportunitiesByStatusLostAndIndustry() {
        return opportunityRepository.findAllByStatusAndIndustry(Status.CLOSED_LOST);
    }

    // Count of all OPEN status Opportunities by Industry
    public List<Object[]> getOpportunitiesByStatusOpenAndIndustry() {
        return opportunityRepository.findAllByStatusAndCountry(Status.OPEN);
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
