package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

    @Query(value = "SELECT sales_rep.name, COUNT(*) FROM Lead INNER JOIN SalesRep ON leads.sales_rep_id = sales_rep.sales_rep_id GROUP BY leads.sales_rep_id", nativeQuery = true)
    List<Object[]> reportLeadBySalesRepId();
}
