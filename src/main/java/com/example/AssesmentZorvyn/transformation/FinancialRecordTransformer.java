package com.example.AssesmentZorvyn.transformation;

import com.example.AssesmentZorvyn.dto.request.FinancialRecordRequest;
import com.example.AssesmentZorvyn.dto.response.FinancialRecordResponse;
import com.example.AssesmentZorvyn.models.FinancialRecord;

import java.time.LocalDate;

public class FinancialRecordTransformer {
    public static FinancialRecord financialRecordRequestToFinancialRecord(FinancialRecordRequest financialRecordRequest) {
        FinancialRecord financialRecord = FinancialRecord.builder()
                .amount(financialRecordRequest.getAmount())
                .type(financialRecordRequest.getType())
                .category(financialRecordRequest.getCategory())
                .note(financialRecordRequest.getNote())
                .date(LocalDate.now())
                .build();

        return financialRecord;
    }

    public static FinancialRecordResponse financialRecordToFinancialRecordResponse(FinancialRecord savedFinancialRecord) {
        FinancialRecordResponse financialRecordResponse = FinancialRecordResponse.builder()
                .id(savedFinancialRecord.getId())
                .amount(savedFinancialRecord.getAmount())
                .type(savedFinancialRecord.getType())
                .category(savedFinancialRecord.getCategory())
                .date(savedFinancialRecord.getDate())
                .note(savedFinancialRecord.getNote())
                .userResponse(UserTransformer.userToUserResponse(savedFinancialRecord.getUser()))
                .updatedBy(savedFinancialRecord.getUpdatedBy())
                .build();

        return financialRecordResponse;
    }
}
