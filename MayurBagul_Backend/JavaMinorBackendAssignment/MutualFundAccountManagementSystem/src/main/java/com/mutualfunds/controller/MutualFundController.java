package com.mutualfunds.controller;

import com.mutualfunds.dto.MutualFundDTO;
import com.mutualfunds.model.MutualFund;
import com.mutualfunds.service.MutualFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class MutualFundController {

    private final MutualFundService mutualFundService;

    // Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MutualFund> addFund(@RequestBody MutualFundDTO dto) {
        return ResponseEntity.ok(mutualFundService.addFund(dto));
    }

    // Public/User
    @GetMapping
    public ResponseEntity<List<MutualFund>> getAllFunds() {
        return ResponseEntity.ok(mutualFundService.getAllFunds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MutualFund> getFundById(@PathVariable Long id) {
        return ResponseEntity.ok(mutualFundService.getFundById(id));
    }
}
