package com.ironhack.WondercodersCRMDataLayer.Model;

import com.ironhack.WondercodersCRMDataLayer.Enums.Industry;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "account" )
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    @Column(name = "employee_count")
    private int employeeCount;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "account")
    private List<Contact> contactList;

    @OneToMany(mappedBy = "account")
    private List<Opportunity> opportunityList;

    public Account() {
    }

    public Account(String companyName, Industry industry, int employeeCount, String city, String country) {
        this.companyName = companyName;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = new ArrayList<>();
        this.opportunityList = new ArrayList<>();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return employeeCount == account.employeeCount && Objects.equals(accountId, account.accountId) && Objects.equals(companyName, account.companyName) && industry == account.industry && Objects.equals(city, account.city) && Objects.equals(country, account.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, companyName, industry, employeeCount, city, country);
    }
}
