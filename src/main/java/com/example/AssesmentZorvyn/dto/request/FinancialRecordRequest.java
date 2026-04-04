package com.example.AssesmentZorvyn.dto.request;

import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.annotations.MinWords;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class FinancialRecordRequest {

    @NotBlank
    private Double amount;

    @NotNull
    private Type type;

    @NotBlank
    private String category;

    @NotBlank
    @MinWords(value = 10, message = "Content must have at least 10 words")
    private String note;

}
