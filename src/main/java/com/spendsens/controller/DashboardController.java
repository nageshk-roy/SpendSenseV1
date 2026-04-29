package com.spendsens.controller;

import com.spendsens.dto.*;
import com.spendsens.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final TransactionService transactionService;
    private final InsightService insightService;

    public DashboardController(TransactionService transactionService, InsightService insightService) {
        this.transactionService = transactionService;
        this.insightService = insightService;
    }

    @GetMapping("/dashboard/summary")
    public ResponseEntity<DashboardSummaryDto> getSummary(
        Authentication auth,
        @RequestParam int month,
        @RequestParam int year
    ) {
        return ResponseEntity.ok(transactionService.getDashboardSummary((Long) auth.getPrincipal(), month, year));
    }

    @GetMapping("/insights")
    public ResponseEntity<List<InsightDto>> getInsights(
        Authentication auth,
        @RequestParam int month,
        @RequestParam int year
    ) {
        return ResponseEntity.ok(insightService.generateInsights((Long) auth.getPrincipal(), month, year));
    }
}
