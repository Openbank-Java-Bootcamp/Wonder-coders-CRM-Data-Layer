package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepRepositoryTest {

    @Autowired
    private SalesRepRepository repo;
    private SalesRep rep1;

    @BeforeEach
    void setUp() {
        rep1 = new SalesRep(1, "Ana");
        repo.save(rep1);
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    public void TestJPA() {
        Optional<SalesRep> salesRepFromDb = repo.findById(1);
        // Test if it exists
        assertTrue(salesRepFromDb.isPresent());
        // Test if the object was created correctly with same info
        assertEquals(rep1,salesRepFromDb.get());
    }

    @Test
    void findBySalesRepId() {
        SalesRep salesRepFromDb = repo.findBySalesRepId(1);
        // Test if it exists
        assertTrue(repo.findAll().contains(salesRepFromDb));
        // Test if the object was created correctly with same info
        assertEquals(rep1,salesRepFromDb);
    }

    @Test
    void findByName() {
        SalesRep salesRepFromDb = repo.findByName("Ana");
        // Test if it exists
        assertTrue(repo.findAll().contains(salesRepFromDb));
        // Test if the object was created correctly with same info
        assertEquals(rep1,salesRepFromDb);
    }
}