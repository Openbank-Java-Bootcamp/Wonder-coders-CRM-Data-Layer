package com.ironhack.WondercodersCRMDataLayer.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int contactId;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @OneToOne(mappedBy = "decisionMaker")
    private Opportunity opportunity;

    @ManyToOne
    @JoinColumn (name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn (name = "sales_rep_id")
    private SalesRep salesRepId;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        Contact contact = (Contact) o;
        return contactId == contact.contactId && Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(email, contact.email) && Objects.equals(opportunity, contact.opportunity) && Objects.equals(account, contact.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, name, phoneNumber, email, opportunity, account);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", opportunity=" + opportunity +
                ", account=" + account +
                '}';
    }
}
