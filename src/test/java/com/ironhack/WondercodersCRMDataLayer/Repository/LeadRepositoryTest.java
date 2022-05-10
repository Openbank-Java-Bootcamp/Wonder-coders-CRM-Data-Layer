package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Lead;
import com.ironhack.WondercodersCRMDataLayer.Model.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LeadRepositoryTest {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;


    /*@BeforeEach
    void setUp() {
        salesRepRepository.saveAll(List.of(
                new SalesRep("pepe"),
                new SalesRep("pepa"),
                new SalesRep("pape")

        ));
        leadRepository.save(new Lead("Olatz","645739689", "olatzizagirre@hotmail.com", "Ironhack",salesRepRepository.findById(1).get()));
        leadRepository.save(new Lead("Peter","645673678", "peter@hotmail.com", "Openbank",salesRepRepository.findById(2).get()));
        leadRepository.save(new Lead("Lucas","665289068", "lucas@hotmail.com", "Mercadona",salesRepRepository.findById(1).get()));
    }*/

    /*@AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }*/

    @Test
    public void reportLeadBySalesRepId_ValidDate_LeadCount(){
        List<Object[]> count = leadRepository.reportLeadBySalesRepId();
        assertEquals(BigInteger.valueOf(2), count.get(0)[1]);

    }

    @Test
    public void findById_ValidDate_LeadName(){
        assertEquals("Olatz",leadRepository.findById(1).get().getName());

    }


}