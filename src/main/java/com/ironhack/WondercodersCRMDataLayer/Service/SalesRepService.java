package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Model.SalesRep;
import com.ironhack.WondercodersCRMDataLayer.Repository.SalesRepRepository;
import com.ironhack.WondercodersCRMDataLayer.classes.App;
import com.ironhack.WondercodersCRMDataLayer.classes.AppHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesRepService {

    @Autowired
    private SalesRepRepository salesRepRepository;
    App app = new App();

    public void getAllSalesReps() {
        List<SalesRep> reps = salesRepRepository.findAll();

        // Title for commands table:
        String title = "SALES REPS LIST";
        // Headers set the size of each column:
        String[] headers = {"ID              ", "NAME                      "};
        // Convert List<SalesRep> to List<String[]>
        List<String[]> list = new ArrayList<>();
        reps.forEach((rep) -> {
            String[] arr = {rep.getSalesRepId().toString(), rep.getName()};
            list.add(arr);
        });
        // Execute printTable method:
        AppHelp.printTable(title, headers, list );
    }

    public void addNewSalesRep() {
        SalesRep rep = new SalesRep(AppHelp.askForString("Please introduce the name for the new sales rep."));
        salesRepRepository.save(rep);
        // Title for commands table:
        String title = "SALES REPS LIST";
        // Headers set the size of each column:
        String[] headers = {"ID              ", "NAME                      "};
        // Convert List<SalesRep> to List<String[]>
        List<String[]> list = new ArrayList<>();
        String[] arr = {rep.getSalesRepId().toString(), rep.getName()};
        list.add(arr);
        // Execute printTable method:
        AppHelp.printTable(title, headers, list );

    }
    public void findSalesRepById() {
        SalesRep rep = salesRepRepository.findBySalesRepId(Integer.parseInt(AppHelp.getId()));
        // Title for commands table:
        String title = "SALES REPS LIST";
        // Headers set the size of each column:
        String[] headers = {"ID              ", "NAME                      "};
        // Convert List<SalesRep> to List<String[]>
        List<String[]> list = new ArrayList<>();
        String[] arr = {rep.getSalesRepId().toString(), rep.getName()};
        list.add(arr);
        // Execute printTable method:
        AppHelp.printTable(title, headers, list );

    }
}
