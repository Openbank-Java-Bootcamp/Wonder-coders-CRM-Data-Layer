package com.ironhack.WondercodersCRMDataLayer.Service;

import com.ironhack.WondercodersCRMDataLayer.Model.Account;
import com.ironhack.WondercodersCRMDataLayer.Repository.AccountRepository;
import com.ironhack.WondercodersCRMDataLayer.classes.App;
import com.ironhack.WondercodersCRMDataLayer.classes.AppHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    App app;


    public void showAccounts() {
        List<Account> accountList = accountRepository.findAll();
        String title = "ACCOUNT LIST";
        String[] headers = {"ACCOUNT-ID      ", "COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         ", "CONTACTS                      ", "OPPORTUNITIES                 "};

        List<String[]> list = new ArrayList<>();
        for (Account account : accountList) {
            String[] array = {account.getAccountId().toString(), account.getCompanyName(), account.getIndustry().toString(), String.valueOf(account.getEmployeeCount()), account.getCity(), account.getCountry(), account.getContactList().toString(), account.getOpportunityList().toString()};
            list.add(array);
        }
        AppHelp.printTable(title, headers, list);
    }

    public void showAccount(Account account) {
        String title = "ACCOUNT ";
        String[] headers = {"ACCOUNT-ID      ", "COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         ", "CONTACTS                      ", "OPPORTUNITIES                 "};

        List<String[]> list = new ArrayList<>();
        String[] array = {account.getAccountId().toString(), account.getCompanyName(), account.getIndustry().toString(), String.valueOf(account.getEmployeeCount()), account.getCity(), account.getCountry(), account.getContactList().toString(), account.getOpportunityList().toString()};
        list.add(array);

        AppHelp.printTable(title, headers, list);

    }

    public void lookUpOpportunity() {
        int id = Integer.parseInt(app.getCurrentId());
        Optional<Account> accountFromDB = accountRepository.findById(id);
        if (accountFromDB.isPresent()) {
            String title = "THIS IS THE ACCOUNT WITH ID " + id;

            String[] headers = {"ACCOUNT-ID      ", "COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         ", "CONTACTS                      ", "OPPORTUNITIES                 "};

            List<String[]> list = new ArrayList<>();
            String[] array = {accountFromDB.get().getAccountId().toString(), accountFromDB.get().getIndustry().toString(), String.valueOf(accountFromDB.get().getEmployeeCount()), accountFromDB.get().getCity(), accountFromDB.get().getCountry(), accountFromDB.get().getContactList().toString(), accountFromDB.get().getOpportunityList().toString() };
            list.add(array);

            AppHelp.printTable(title, headers, list);

        } else {
            System.err.println("No accounts with" + id + "number");
        }
    }


}
