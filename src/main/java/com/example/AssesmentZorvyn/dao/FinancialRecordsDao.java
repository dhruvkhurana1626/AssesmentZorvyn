package com.example.AssesmentZorvyn.dao;

import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.models.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancialRecordsDao extends JpaRepository<FinancialRecord,Long> {

    List<FinancialRecord> findByType(Type type);
    List<FinancialRecord> findByCategory(String category);

    //total amount based on type
    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = :type")
    Double sumByType(@Param("type") Type type);

    //category wise
    @Query("SELECT r.category, SUM(r.amount) FROM FinancialRecord r GROUP BY r.category")
    List<Object[]> getCategorySummary();

    //all records in date wise order - from - new first
    @Query("SELECT f FROM FinancialRecord f ORDER BY f.date")
    List<FinancialRecord> findAllInSortedOrder();
}
