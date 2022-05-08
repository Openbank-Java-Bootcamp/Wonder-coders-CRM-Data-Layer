package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Enums.Product;
import com.ironhack.WondercodersCRMDataLayer.Enums.Status;
import com.ironhack.WondercodersCRMDataLayer.Model.Opportunity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityRepositoryTest {
    @Autowired
    private OpportunityRepository opportunityRepository;

    private List<Opportunity> opportunities;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}