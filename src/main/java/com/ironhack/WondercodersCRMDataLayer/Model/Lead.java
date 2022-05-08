package com.ironhack.WondercodersCRMDataLayer.Model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "lead_id")
    private int leadId;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;


    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn (name = "sales_rep_id")
    private SalesRep salesRepId;


    public Lead() {
    }

    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRepId = salesRep;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SalesRep getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(SalesRep salesRepId) {
        this.salesRepId = salesRepId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return leadId == lead.leadId && Objects.equals(name, lead.name) && Objects.equals(phoneNumber, lead.phoneNumber) && Objects.equals(email, lead.email) && Objects.equals(companyName, lead.companyName) && Objects.equals(salesRepId, lead.salesRepId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leadId, name, phoneNumber, email, companyName, salesRepId);
    }

    @Override
    public String toString() {
        return "Lead{" +
                "leadId=" + leadId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", salesRep=" + salesRepId +
                '}';
    }
}
