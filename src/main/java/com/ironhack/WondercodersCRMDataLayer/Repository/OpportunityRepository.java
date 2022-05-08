package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Enums.*;
import com.ironhack.WondercodersCRMDataLayer.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    /****** Sales Rep ******/
    @Query("SELECT COUNT(*)" +
            "FROM Opportunity" +
            "GROUP BY salesRep")
    List<Opportunity> findAllBySalesRep(); // All Opportunities by SalesRep

    @Query("SELECT COUNT(*)" +
            "FROM Opportunity" +
            "GROUP BY salesRep HAVING opportunity.status = :status")
    List<Opportunity> findAllByStatusAndSalesRep(@Param("status") Status status);

    /****** Product ******/
    @Query("SELECT COUNT(*)" +
            "FROM Opportunity" +
            "GROUP BY product")
    List<Opportunity> findAllByProduct(); // All Opportunities by the product

    @Query("SELECT COUNT(*)" +
            "FROM Opportunity" +
            "GROUP BY product HAVING opportunity.status = :product")
    List<Opportunity> findAllByStatusWonAndProduct(@Param("product") Product product);

    /****** Country ******/
    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.country", nativeQuery = true)
    List<Object[]> findAllByCountry(); // All Opportunities by the country

    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.country HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusWonAndCountry(@Param("status") Status status);

    /****** City ******/
    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.city", nativeQuery = true)
    List<Object[]> findAllByCity(); // All Opportunities by city

    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.city HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusWonAndCity(@Param("status") Status status);

    /****** Industry ******/
    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.industry", nativeQuery = true)
    List<Object[]> findAllByIndustry(); // All Opportunities by industry

    @Query(value = "SELECT COUNT(*)" +
            "FROM Opportunity" +
            "INNER JOIN Account ON opportunity.account = account.account_id" +
            "GROUP BY account.industry HAVING opportunity.status = :status", nativeQuery = true)
    List<Object[]> findAllByStatusWonAndIndustry(@Param("status") Status status);

    /****** Quantity ******/
    @Query("SELECT product, AVG(quantity) AS Mean quantity" +
            "FROM opportunity" +
            "GROUP BY product")
    List<Object[]> meanQuantity(); // Mean quantity of products order

    @Query("SELECT TOP 1" +
            "PERCENTILE_CONT(0.5) WITHIN GROUP product OVER() AS Media quantity" +
            "FROM opportunity")
    List<Object[]> medianQuantity(); // Median quantity of products order

    @Query("SELECT product, MAX(quantity) AS Maximum quantity" +
            "FROM opportunity" +
            "GROUP BY product")
    List<Object[]> maxQuantity(); // Maximum quantity of products order

    @Query("SELECT product, MIN(quantity) AS Minimum quantity" +
            "FROM opportunity" +
            "GROUP BY product")
    List<Object[]> minQuantity(); // Minimum quantity of products order

    /****** Opportunity ******/
    @Query("SELECT account, AVG(COUNT(*)) AS Mean quantity" +
            "FROM opportunity" +
            "GROUP BY account")
    List<Object[]> meanOpportunities(); // Mean number of Opportunities associated with an Account

    @Query("SELECT TOP 1" +
            "PERCENTILE_CONT(0.5) WITHIN GROUP account OVER() AS Media quantity" +
            "FROM opportunity")
    List<Object[]> medianOpportunities(); // Median number of Opportunities associated with an Account

    @Query("SELECT account, MAX(COUNT(*)) AS Maximum quantity" +
            "FROM opportunity" +
            "GROUP BY account")
    List<Object[]> maxOpportunities(); // Maximum number of Opportunities associated with an Account

    @Query("SELECT account, MIN(COUNT(*)) AS Minimum quantity" +
            "FROM opportunity" +
            "GROUP BY account")
    List<Object[]> minOpportunities(); // Minimum number of Opportunities associated with an Account
}
