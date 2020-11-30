package com.allstate.dbservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allstate.dbservices.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query(value = "SELECT * FROM hrms.tax WHERE province = ?1 AND  tax_bracket_min <= ?2 AND tax_bracket_max >= ?2", nativeQuery = true)
    public Tax findByProvinceAndSalary(String province, Long salaryAmount);
}
