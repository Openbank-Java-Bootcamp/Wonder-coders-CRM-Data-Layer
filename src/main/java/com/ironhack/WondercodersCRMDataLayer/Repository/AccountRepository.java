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
    @Query(value = "SELECT AVG(employee_count) AS Mean_Count FROM account", nativeQuery = true)
    double meanEmployees();

    //The median employeeCount
    @Query(value = "SELECT TOP 1 PERCENTILE_CONT(0.5) WITHIN GROUP employee_count OVER() AS Media_Count FROM account", nativeQuery = true)
    double medianEmployees();

    @Query(value = "SELECT MAX(employee_count) AS Maximum_Count FROM account", nativeQuery = true)
    double maxEmployeeCount();

    @Query(value = "SELECT MIN(employee_count) AS Minimum_Count FROM account", nativeQuery = true)
    double minEmployeeCount();





}
