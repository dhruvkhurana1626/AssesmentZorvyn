package com.example.AssesmentZorvyn.controller;

import com.example.AssesmentZorvyn.dto.request.FinancialRecordRequest;
import com.example.AssesmentZorvyn.dto.response.FinancialRecordResponse;
import com.example.AssesmentZorvyn.enums.Type;
import com.example.AssesmentZorvyn.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/records")
@PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
public class FinancialRecordController {

    private final FinancialRecordService financialRecordService;

    //create record - admin & analyst
    @PostMapping
    public ResponseEntity addFinancialRecord(@RequestParam Long userId,
                                             @RequestBody FinancialRecordRequest financialRecordRequest){
        FinancialRecordResponse financialRecordResponse =
                financialRecordService.addFinancialRecord(userId,financialRecordRequest);

        return ResponseEntity.ok(financialRecordResponse);
    }

    //get all records - access by all
    @GetMapping
    public ResponseEntity getAllRecords() {
        return ResponseEntity.ok(financialRecordService.getAllRecords());
    }

    //delele record - admin and analyst
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecord(@PathVariable Long id) {
        return ResponseEntity.ok(financialRecordService.deleteRecord(id));
    }

    // Update Record (ADMIN + ANALYST)
    @PutMapping("/{id}")
    public ResponseEntity updateRecord(
            @PathVariable Long id,
            @RequestBody FinancialRecordRequest request) {
        return ResponseEntity.ok(financialRecordService.updateRecord(id, request));
    }

    //filter by type
    @GetMapping("/filter/type")
    public ResponseEntity getByType(
            @RequestParam Type type) {
        return ResponseEntity.ok(financialRecordService.getByType(type));
    }

    //filter by category
    @GetMapping("/filter/category")
    public ResponseEntity getByCategory(
            @RequestParam String category) {
        return ResponseEntity.ok(financialRecordService.getByCategory(category));
    }

}
