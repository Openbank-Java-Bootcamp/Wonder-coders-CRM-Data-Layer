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

        accounts = accountRepository.saveAll(List.of(
                new Account("Mercadona", Industry.MEDICAL, 30, "Sueca", "Spain"),
                new Account("Consum", Industry.PRODUCE, 40, "Cullera", "Spain")
        ));

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("Rep"),
                new SalesRep("Rep2")
        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Product.FLATBED, 40, Status.OPEN, contacts.get(0), accounts.get(0), salesReps.get(0)),
                new Opportunity(Product.BOX, 20, Status.CLOSED_LOST, contacts.get(1), accounts.get(1), salesReps.get(1))
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
    }
}