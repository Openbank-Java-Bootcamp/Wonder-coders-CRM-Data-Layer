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

    // Show a list of accounts
    public void showAccounts() {
        List<Account> accountList = accountRepository.findAll();
        String title = "ACCOUNT LIST";
        String[] headers = {"ACCOUNT-ID      ", "COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         "};

        List<String[]> list = new ArrayList<>();
        for (Account account : accountList) {
            String[] array = {account.getAccountId().toString(), account.getCompanyName(), account.getIndustry().toString(), String.valueOf(account.getEmployeeCount()), account.getCity(), account.getCountry()};
            list.add(array);
        }
        AppHelp.printTable(title, headers, list);
    }

    // Show an account details
    public void showAccount(Account account) {
        String title = "ACCOUNT ";
        String[] headers = {"COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         "};

        List<String[]> list = new ArrayList<>();
        String[] array = {account.getCompanyName(), account.getIndustry().toString(), String.valueOf(account.getEmployeeCount()), account.getCity(), account.getCountry()};
        list.add(array);

        AppHelp.printTable(title, headers, list);

    }

    // Returns an account by id
    public void lookUpAccount() {
        int id = Integer.parseInt(AppHelp.getId());
        Optional<Account> accountFromDB = accountRepository.findById(id);
        if (accountFromDB.isPresent()) {
            String title = "THIS IS THE ACCOUNT WITH ID " + id;

            String[] headers = {"ACCOUNT-ID      ", "COMPANY NAME    ", "INDUSTRY        ", "EMPLOYEES NUMBER", "CITY            ", "COUNTRY         "};

            List<String[]> list = new ArrayList<>();
            String[] array = {accountFromDB.get().getAccountId().toString(), accountFromDB.get().getIndustry().toString(), String.valueOf(accountFromDB.get().getEmployeeCount()), accountFromDB.get().getCity(), accountFromDB.get().getCountry() };
            list.add(array);

            AppHelp.printTable(title, headers, list);

        } else {
            System.err.println("No accounts with" + id + "number");
        }
    }

    //The mean employeeCount
    public void  getMeanEmployees() {
        double meanEmployeesFromDb = accountRepository.meanEmployees();
        if (accountRepository.findAll().isEmpty()) {
            System.err.println("There are not enough data");
        }else {
            String title = "MEAN QUANTITY REPORT OF EMPLOYEES COUNT";
            String[] headers = {"MEAN EMPLOYEES COUNT                              "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(meanEmployeesFromDb)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    //The median employeeCount
    public void getMedianEmployees() {
        double medianEmployeesFromDb = accountRepository.medianEmployees();
        if (accountRepository.findAll().isEmpty()) {
            System.err.println("There are not enough data");
        }else {
            String title = "MEDIAN QUANTITY REPORT OF EMPLOYEES COUNT";
            String[] headers = {"MEDIAN EMPLOYEES COUNT                              "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(medianEmployeesFromDb)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    //The maximum employeeCount
    public void getMaxEmployeeCount() {
        double maxEmployeesFromDb = accountRepository.maxEmployeeCount();
        if (accountRepository.findAll().isEmpty()) {
            System.err.println("There are not enough data");
        }else {
            String title = "MAX NUMBER REPORT OF EMPLOYEES COUNT";
            String[] headers = {"MAX NUMBER EMPLOYEES COUNT                              "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(maxEmployeesFromDb)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

    //The minimum employeeCount
    public void getMinEmployeeCount() {
        double minEmployeesFromDb = accountRepository.minEmployeeCount();
        if (accountRepository.findAll().isEmpty()) {
            System.err.println("There are not enough data");
        }else {
            String title = "MIN NUMBER REPORT OF EMPLOYEES COUNT";
            String[] headers = {"MIN NUMBER EMPLOYEES COUNT                              "};
            List<String[]> list = new ArrayList<>();
            String[] values = {String.valueOf(minEmployeesFromDb)};
            list.add(values);
            AppHelp.printTable(title, headers, list);
        }
    }

}
