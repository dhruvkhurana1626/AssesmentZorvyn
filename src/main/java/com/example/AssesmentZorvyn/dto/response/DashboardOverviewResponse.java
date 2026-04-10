package com.example.AssesmentZorvyn.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardOverviewResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    private List<FinancialRecordResponse> recordResponseList;
}
