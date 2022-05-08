package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {

    // Queries:
    SalesRep findBySalesRepId(Integer id);
    SalesRep findByName(String name);

}
