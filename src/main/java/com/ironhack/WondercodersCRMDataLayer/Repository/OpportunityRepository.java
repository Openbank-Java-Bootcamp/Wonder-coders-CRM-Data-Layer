package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Enums.*;
import com.ironhack.WondercodersCRMDataLayer.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    @Query(value = "SELECT sales_rep.name, COUNT(*) FROM Opportunity INNER JOIN sales_rep ON opportunity.sales_rep = sales_rep.sales_rep_id GROUP BY sales_rep", nativeQuery = true)
    List<Object[]> findAllBySalesRepId(); // All Opportunities by SalesRep

    @Query(value = "SELECT sales_rep.name, opportunity.status, COUNT(*) FROM Opportunity INNER JOIN sales_rep ON opportunity.sales_rep = sales_rep.sales_rep_id GROUP BY sales_rep HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusAndSalesRepId(@Param("status") Status status); // All Status Opportunities by SalesRep

    @Query(value = "SELECT product, COUNT(*) FROM Opportunity GROUP BY product", nativeQuery = true)
    List<Object[]> findAllByProduct(); // All Opportunities by the product

    @Query(value = "SELECT product, COUNT(*) FROM Opportunity GROUP BY product HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusAndProduct(@Param("status") Status status); // All Status Opportunities by product

    @Query(value = "SELECT account.country, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.country", nativeQuery = true)
    List<Object[]> findAllByCountry(); // All Opportunities by the country

    @Query(value = "SELECT account.country, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.country HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusAndCountry(@Param("status") Status status); // All Status Opportunities by country

    @Query(value = "SELECT account.city, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.city", nativeQuery = true)
    List<Object[]> findAllByCity(); // All Opportunities by city

    @Query(value = "SELECT account.city, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.city HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusAndCity(@Param("status") Status status); // All Status Opportunities by city

    @Query(value = "SELECT account.industry, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.industry", nativeQuery = true)
    List<Object[]> findAllByIndustry(); // All Opportunities by industry

    @Query(value = "SELECT account.industry, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.industry HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusAndIndustry(@Param("status") Status status); // All Status Opportunities by industry

    @Query(value = "SELECT AVG(quantity) AS Mean quantity FROM opportunity", nativeQuery = true)
    double meanQuantity(); // Mean quantity of products order

    @Query(value = "SELECT TOP 1 PERCENTILE_CONT(0.5) OVER() AS Media quantity FROM opportunity", nativeQuery = true)
    double medianQuantity(); // Median quantity of products order

    @Query(value = "SELECT MAX(quantity) AS Maximum quantity FROM opportunity", nativeQuery = true)
    double maxQuantity(); // Maximum quantity of products order

    @Query(value = "SELECT MIN(quantity) AS Minimum quantity FROM opportunity", nativeQuery = true)
    double minQuantity(); // Minimum quantity of products order

    @Query(value = "SELECT account, AVG(COUNT(*)) AS Mean quantity FROM opportunity GROUP BY account", nativeQuery = true)
    List<Object[]> meanOpportunities(); // Mean number of Opportunities associated with an Account

    @Query(value = "SELECT account, TOP 1 PERCENTILE_CONT(0.5) WITHIN GROUP account OVER() AS Media quantity FROM opportunity", nativeQuery = true)
    List<Object[]> medianOpportunities(); // Median number of Opportunities associated with an Account

    @Query(value = "SELECT account, MAX(COUNT(*)) AS Maximum quantity FROM opportunity GROUP BY account", nativeQuery = true)
    List<Object[]> maxOpportunities(); // Maximum number of Opportunities associated with an Account

    @Query(value = "SELECT account, MIN(COUNT(*)) AS Minimum quantity FROM opportunity GROUP BY account", nativeQuery = true)
    List<Object[]> minOpportunities(); // Minimum number of Opportunities associated with an Account
}
