package com.mutualfunds.controller;

import com.mutualfunds.dto.PortfolioSummaryDTO;
import com.mutualfunds.dto.TransactionDTO;
import com.mutualfunds.model.Transaction;
import com.mutualfunds.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy")
    public ResponseEntity<Transaction> buy(@RequestBody TransactionDTO dto, Authentication auth) {
        return ResponseEntity.ok(transactionService.buyFund(dto, auth));
    }

    @PostMapping("/sell")
    public ResponseEntity<Transaction> sell(@RequestBody TransactionDTO dto, Authentication auth) {
        return ResponseEntity.ok(transactionService.sellFund(dto, auth));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(Authentication auth) {
        return ResponseEntity.ok(transactionService.getAllTransactions(auth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(transactionService.getTransactionById(id, auth));
    }
    
    @GetMapping("/portfolio")
    public ResponseEntity<List<PortfolioSummaryDTO>> getPortfolio(Authentication auth) {
        return ResponseEntity.ok(transactionService.getPortfolioSummary(auth));
    }

}
