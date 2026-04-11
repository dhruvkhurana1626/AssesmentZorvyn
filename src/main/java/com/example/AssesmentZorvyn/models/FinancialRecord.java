package com.example.AssesmentZorvyn.models;

import com.example.AssesmentZorvyn.enums.Category;
import com.example.AssesmentZorvyn.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate date;

    @Column
    private String note;

    @Column(nullable = false)
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
