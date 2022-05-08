/*
package com.ironhack.WondercodersCRMDataLayer.Controller;

import com.ironhack.WondercodersCRMDataLayer.Model.SalesRep;
import com.ironhack.WondercodersCRMDataLayer.Service.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesRepController {

    @Autowired
    private SalesRepService salesRepService;

    @GetMapping("/sales-reps")
    public List<SalesRep> getAllSalesReps() {
        return salesRepService.getAllSalesReps();
    }

    @GetMapping("/sales-reps")
    public SalesRep addNewSalesRep(@RequestBody SalesRep salesRep) { return salesRepService.addNewSalesRep(salesRep); }
}
 */