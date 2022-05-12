package com.ironhack.WondercodersCRMDataLayer.Model;

import com.ironhack.WondercodersCRMDataLayer.Enums.*;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "opportunity")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opportunity_id")
    private Integer opportunityId;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "decision_maker")
    private Contact decisionMaker;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "sales_rep")
    private SalesRep salesRepId;

    public Opportunity() {
    }

    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesRepId) {
        this.product = product;
        this.quantity = quantity;
        this.status = Status.OPEN;
        this.decisionMaker = decisionMaker;
        this.salesRepId = salesRepId;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
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
        Opportunity that = (Opportunity) o;
        return quantity == that.quantity && Objects.equals(opportunityId, that.opportunityId) && product == that.product && status == that.status && Objects.equals(decisionMaker, that.decisionMaker) && Objects.equals(account, that.account) && Objects.equals(salesRepId, that.salesRepId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opportunityId, product, quantity, status, decisionMaker, account, salesRepId);
    }

    @Override
    public String toString() {
        return "Opportunity Id: " + opportunityId +
                ", status: " + status;
    }
}
