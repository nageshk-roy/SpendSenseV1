package com.spendsens.controller;

import com.spendsens.dto.*;
import com.spendsens.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<TransactionDto>> getTransactions(
        Authentication auth,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String type
    ) {
        Long userId = (Long) auth.getPrincipal();
        return ResponseEntity.ok(transactionService.getTransactions(userId, page, size, startDate, endDate, category, type));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(Authentication auth, @RequestBody TransactionRequest req) {
        return ResponseEntity.ok(transactionService.createTransaction((Long) auth.getPrincipal(), req));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<TransactionDto>> bulkSync(Authentication auth, @RequestBody List<TransactionRequest> requests) {
        return ResponseEntity.ok(transactionService.bulkSync((Long) auth.getPrincipal(), requests));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable Long id) {
        transactionService.deleteTransaction((Long) auth.getPrincipal(), id);
        return ResponseEntity.noContent().build();
    }
}
