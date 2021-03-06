package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Enums.*;
import com.ironhack.WondercodersCRMDataLayer.Model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityRepositoryTest {
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;

    private List<Opportunity> opportunities;
    private List<Contact> contacts;
    private List<Account> accounts;
    private List<SalesRep> salesReps;

    @BeforeEach
    void setUp() {
        contacts = contactRepository.saveAll(List.of(
                new Contact("Nuria", "123456789", "nuria@gmail.com"),
                new Contact("Bea", "987654321", "bea@gmail.com"),
                new Contact("Olatz", "112233445", "olatz@gmail.com"),
                new Contact("Paula", "998877665", "olatz@gmail.com")
        ));

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep(1,"Rep"),
                new SalesRep(2,"Rep2")
        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Product.FLATBED, 40, contacts.get(0), salesReps.get(0)),
                new Opportunity(Product.BOX, 20, contacts.get(1), salesReps.get(1)),
                new Opportunity(Product.HYBRID, 30, contacts.get(2), salesReps.get(0))
        ));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Test
    public void findAllBySalesRepId_ValidData_ListOpportunities() {
        List<Object[]> result = opportunityRepository.findAllBySalesRepId();
        assertEquals(2, result.size());
        assertEquals("Rep", result.get(0)[0]);
    }

    @Test
    public void findAllByStatusAndSalesRepId_ValidData_ListOpportunities() {
        List<Object[]> result = opportunityRepository.findAllByStatusAndSalesRepId(Status.CLOSED_WON.toString());
        assertEquals(1, result.size());
        assertEquals("Rep2", result.get(0)[0]);
    }
}