package com.example.AssesmentZorvyn.models;

import com.example.AssesmentZorvyn.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "financialRecords")

public class FinancialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String category;

    @Column
    private LocalDate date;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
