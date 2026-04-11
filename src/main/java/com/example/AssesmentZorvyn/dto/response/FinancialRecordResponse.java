package com.example.AssesmentZorvyn.dto.response;

import com.example.AssesmentZorvyn.enums.Category;
import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.models.User;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class FinancialRecordResponse {

    private Long id;
    private Double amount;
    private Type type;
    private Category category;
    private LocalDate date;
    private String note;
    private UserResponse userResponse;
    private String updatedBy;

}
