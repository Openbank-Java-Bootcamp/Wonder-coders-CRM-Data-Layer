package com.ironhack.WondercodersCRMDataLayer.Model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sales_rep")
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_rep_id")
    private Integer salesRepId;

    private String name;

    @OneToMany(mappedBy = "salesRepId")
    private Set<Lead> leads;

    @OneToMany(mappedBy = "salesRepId")
    private Set<Opportunity> opportunities;

    @OneToMany(mappedBy = "salesRepId")
    private Set<Contact> contacts;

    // Constructors:
    public SalesRep() {}
    public SalesRep(String name) { this.name = name; }

    public SalesRep(Integer salesRepId, String name) {
        this.salesRepId = salesRepId;
        this.name = name;
    }

    // Getters & setters
    public Integer getSalesRepId() { return salesRepId; }
    public void setSalesRepId(Integer salesRepId) { this.salesRepId = salesRepId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    // Overridden methods
    @Override
    public String toString() {
        return "SalesRep " + salesRepId + ": " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesRep salesRep = (SalesRep) o;
        return Objects.equals(salesRepId, salesRep.salesRepId) && Objects.equals(name, salesRep.name);
    }

}
