package com.ironhack.WondercodersCRMDataLayer.Repository;

import com.ironhack.WondercodersCRMDataLayer.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByCompanyName(String companyName);

    // The mean employeeCount
    @Query(value = "SELECT AVG( employeeCount ) AS Mean Employee Count FROM Account", nativeQuery = true)
    double meanEmployees();

    //The median employeeCount
    @Query(value = "SELECT TOP 1 PERCENTILE_CONT(0.5) WITHIN GROUP employeeCount OVER() AS Media quantity FROM account", nativeQuery = true)
    double medianEmployees();

    @Query(value = "SELECT MAX(employeeCount) AS Maximum quantity FROM account", nativeQuery = true)
    double maxEmployeeCount();

    @Query(value = "SELECT MIN(employeeCount) AS Minimum quantity FROM account", nativeQuery = true)
    double minEmployeeCount();





}
