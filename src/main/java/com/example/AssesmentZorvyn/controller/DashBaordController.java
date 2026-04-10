package com.example.AssesmentZorvyn.controller;

import com.example.AssesmentZorvyn.service.DashBaordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashbaord")
@RequiredArgsConstructor
public class DashBaordController {

    private final DashBaordService dashBaordService;

    //OverView
    @GetMapping("/overview")
    public ResponseEntity getOverView(){
        return ResponseEntity.ok(dashBaordService.getOverView());
    }

}
