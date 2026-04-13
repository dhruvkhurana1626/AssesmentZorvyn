package com.example.AssesmentZorvyn.service;

import com.example.AssesmentZorvyn.dao.FinancialRecordsDao;
import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.dto.response.DashboardOverviewResponse;
import com.example.AssesmentZorvyn.dto.response.FinancialRecordResponse;
import com.example.AssesmentZorvyn.enums.Category;
import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.models.FinancialRecord;
import com.example.AssesmentZorvyn.transformation.DashBaordTransformer;
import com.example.AssesmentZorvyn.transformation.FinancialRecordTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBaordService {

    private final UserDao userDao;
    private final FinancialRecordsDao financialRecordsDao;

    public DashboardOverviewResponse getOverView(){
        Double income = financialRecordsDao.sumByType(Type.INCOME);
        Double expense = financialRecordsDao.sumByType(Type.EXPENSE);

        double totalIncome = income!=null ? income : 0;
        double totalExpense = expense!=null ? expense : 0;

        double balance  = totalIncome - totalExpense;

        Pageable pageable = PageRequest.of(0,10, Sort.by("date").descending());

        Page<FinancialRecord> page =  financialRecordsDao.findAll(pageable);
        List<FinancialRecord> financialRecordList = page.getContent();

        List<FinancialRecordResponse> financialRecordResponses = new ArrayList<>();
        for(FinancialRecord financialRecord : financialRecordList){
            financialRecordResponses
                    .add(FinancialRecordTransformer.financialRecordToFinancialRecordResponse(financialRecord));
        }

        DashboardOverviewResponse dashboardOverviewResponse = DashBaordTransformer.getOverView(totalIncome,totalExpense,balance,financialRecordResponses);

        return dashboardOverviewResponse;
    }

    public DashboardOverviewResponse getCategorySummary(Category category) {

        Double income = financialRecordsDao.sumByCategoryWhereTypeIncome(category);
        Double expense = financialRecordsDao.sumByCategoryWhereTypeExpense(category);

        double totalIncome = income!=null ? income : 0;
        double totalExpense = expense!=null ? expense : 0;

        double balance  = totalIncome - totalExpense;

        Pageable pageable = PageRequest.of(0,10, Sort.by("date").descending());

        Page<FinancialRecord> page =  financialRecordsDao.findAllByCategory(category,pageable);
        List<FinancialRecord> financialRecordList = page.getContent();

        List<FinancialRecordResponse> financialRecordResponses = new ArrayList<>();
        for(FinancialRecord financialRecord : financialRecordList){
            financialRecordResponses
                    .add(FinancialRecordTransformer.financialRecordToFinancialRecordResponse(financialRecord));
        }

        DashboardOverviewResponse dashboardOverviewResponse = DashBaordTransformer.getOverView(totalIncome,totalExpense,balance,financialRecordResponses);

        return dashboardOverviewResponse;
    }
}
