package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.ironhack.WondercodersCRMDataLayer.Enums.Industry.ECOMMERCE;
import static com.ironhack.WondercodersCRMDataLayer.Enums.Industry.PRODUCE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account accountTest1;
    private Account accountTest2;

    @BeforeEach
    void setUp() {
        accountTest1 = new Account("Company1", ECOMMERCE ,2000,"Madrid","Spain");
        accountRepository.save(accountTest1);

        accountTest2 = new Account("Company2", PRODUCE ,1000,"Malaga","Spain");
        accountRepository.save(accountTest2);
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll(); ;
    }

    @Test
    public void testFindAll(){
        List<Account> accountFromDb = accountRepository.findAll();
        assertEquals(2,accountFromDb.size());
    }

    @Test
    public void testUpdate(){
        accountTest1.setCity("Zaragoza");
        accountRepository.save(accountTest1);

        Account accountFromDb = accountRepository.findById(accountTest1.getAccountId()).get();
        assertEquals(accountTest1, accountFromDb);
    }

    @Test
    public void testDelete(){
        accountRepository.delete(accountTest1);
        List<Account> accountList = accountRepository.findAll();
        assertEquals(1,accountList.size());
    }







}