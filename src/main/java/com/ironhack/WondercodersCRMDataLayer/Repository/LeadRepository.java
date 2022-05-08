package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

    @Query("SELECT salesRepName,COUNT(lead) FROM Lead GROUP BY salesRep")
    List<Object[]> reportLeadBySalesRep();
}
