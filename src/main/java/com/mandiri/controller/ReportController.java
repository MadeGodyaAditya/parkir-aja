package com.mandiri.controller;

import com.mandiri.dto.CustomPage;
import com.mandiri.entity.Report;
import com.mandiri.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    
    @GetMapping
    public CustomPage<Report> find(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return reportService.find(pageable);
    }
}
