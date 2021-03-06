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

    @Query(value = "SELECT sales_rep.name, COUNT(*) FROM Opportunity INNER JOIN sales_rep ON opportunity.sales_rep = sales_rep.sales_rep_id WHERE opportunity.status = :status GROUP BY sales_rep", nativeQuery = true)
    List<Object[]> findAllByStatusAndSalesRepId(@Param("status") String status); // All Status Opportunities by SalesRep

    @Query(value = "SELECT product, COUNT(*) FROM Opportunity GROUP BY product", nativeQuery = true)
    List<Object[]> findAllByProduct(); // All Opportunities by the product

    @Query(value = "SELECT product, COUNT(*) FROM Opportunity WHERE opportunity.status = :status GROUP BY product", nativeQuery = true)
    List<Object[]> findAllByStatusAndProduct(@Param("status") String status); // All Status Opportunities by product

    @Query(value = "SELECT account.country, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.country", nativeQuery = true)
    List<Object[]> findAllByCountry(); // All Opportunities by the country

    @Query(value = "SELECT account.country, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id WHERE opportunity.status = :status GROUP BY account.country", nativeQuery = true)
    List<Object[]> findAllByStatusAndCountry(@Param("status") String status); // All Status Opportunities by country

    @Query(value = "SELECT account.city, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.city", nativeQuery = true)
    List<Object[]> findAllByCity(); // All Opportunities by city

    @Query(value = "SELECT account.city, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id WHERE opportunity.status = :status GROUP BY account.city", nativeQuery = true)
    List<Object[]> findAllByStatusAndCity(@Param("status") String status); // All Status Opportunities by city

    @Query(value = "SELECT account.industry, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id GROUP BY account.industry", nativeQuery = true)
    List<Object[]> findAllByIndustry(); // All Opportunities by industry

    @Query(value = "SELECT account.industry, COUNT(*) FROM Opportunity INNER JOIN Account ON opportunity.account = account.account_id WHERE opportunity.status = :status GROUP BY account.industry", nativeQuery = true)
    List<Object[]> findAllByStatusAndIndustry(@Param("status") String status); // All Status Opportunities by industry

    @Query(value = "SELECT AVG(quantity) AS mean_quantity FROM opportunity", nativeQuery = true)
    double meanQuantity(); // Mean quantity of products order

    @Query(value = "SELECT quantity FROM opportunity", nativeQuery = true)
    List<Integer> medianQuantityList(); // Median quantity of products order

    @Query(value = "SELECT MAX(quantity) AS maximum_quantity FROM opportunity", nativeQuery = true)
    double maxQuantity(); // Maximum quantity of products order

    @Query(value = "SELECT MIN(quantity) AS minimum_quantity FROM opportunity", nativeQuery = true)
    double minQuantity(); // Minimum quantity of products order

    @Query(value = "SELECT account, AVG(count) AS mean_quantity FROM (SELECT account, COUNT(*) count FROM opportunity GROUP BY account) AS A", nativeQuery = true)
    List<Object[]> meanOpportunities(); // Mean number of Opportunities associated with an Account

    @Query(value = "SELECT COUNT(*) FROM opportunity GROUP BY account", nativeQuery = true)
    List<Integer> medianOpportunitiesList(); // Median number of Opportunities associated with an Account

    @Query(value = "SELECT account, MAX(count) AS mean_quantity FROM (SELECT account, COUNT(*) count FROM opportunity GROUP BY account) AS A", nativeQuery = true)
    List<Object[]> maxOpportunities(); // Maximum number of Opportunities associated with an Account

    @Query(value = "SELECT account, MIN(count) AS mean_quantity FROM (SELECT account, COUNT(*) count FROM opportunity GROUP BY account) AS A", nativeQuery = true)
    List<Object[]> minOpportunities(); // Minimum number of Opportunities associated with an Account
}
