package com.example.AssesmentZorvyn.transformation;

import com.example.AssesmentZorvyn.dto.response.DashboardOverviewResponse;
import com.example.AssesmentZorvyn.dto.response.FinancialRecordResponse;

import java.util.List;

public class DashBaordTransformer {

    public static DashboardOverviewResponse getOverView(Double totalIncome, Double totalExpense, Double balance, List<FinancialRecordResponse> financialRecordResponseList){
        DashboardOverviewResponse dashboardOverviewResponse = DashboardOverviewResponse.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(balance)
                .recordResponseList(financialRecordResponseList)
                .build();

        return dashboardOverviewResponse;
    }
}
