package com.spendsens.controller;

import com.spendsens.dto.*;
import com.spendsens.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getBudgets(
        Authentication auth,
        @RequestParam int month,
        @RequestParam int year
    ) {
        return ResponseEntity.ok(budgetService.getBudgets((Long) auth.getPrincipal(), month, year));
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createBudget(Authentication auth, @RequestBody BudgetRequest req) {
        return ResponseEntity.ok(budgetService.createOrUpdateBudget((Long) auth.getPrincipal(), req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDto> updateBudget(Authentication auth, @PathVariable Long id, @RequestBody BudgetRequest req) {
        return ResponseEntity.ok(budgetService.createOrUpdateBudget((Long) auth.getPrincipal(), req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(Authentication auth, @PathVariable Long id) {
        budgetService.deleteBudget((Long) auth.getPrincipal(), id);
        return ResponseEntity.noContent().build();
    }
}
