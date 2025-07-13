package com.mutualfunds.service;

import com.mutualfunds.dto.PortfolioSummaryDTO;
import com.mutualfunds.dto.TransactionDTO;
import com.mutualfunds.model.*;
import com.mutualfunds.repository.MutualFundRepository;
import com.mutualfunds.repository.TransactionRepository;
import com.mutualfunds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final MutualFundRepository fundRepository;
    private final UserRepository userRepository;

    public Transaction buyFund(TransactionDTO dto, Authentication auth) {
        User user = getCurrentUser(auth);

        MutualFund fund = fundRepository.findById(dto.getMutualFundId())
                .orElseThrow(() -> new RuntimeException("Fund not found"));

        BigDecimal nav = fund.getNav();
        BigDecimal amount = dto.getUnits().multiply(nav);

        Transaction transaction = Transaction.builder()
                .type("BUY")
                .userId(user.getId())
                .mutualFundId(fund.getId())
                .units(dto.getUnits())
                .amount(amount)
                .transactionDate(LocalDate.now())
                .build();

        return transactionRepository.save(transaction);
    }

    public Transaction sellFund(TransactionDTO dto, Authentication auth) {
        User user = getCurrentUser(auth);

        MutualFund fund = fundRepository.findById(dto.getMutualFundId())
                .orElseThrow(() -> new RuntimeException("Fund not found"));

        BigDecimal nav = fund.getNav();
        BigDecimal amount = dto.getUnits().multiply(nav);

        Transaction transaction = Transaction.builder()
                .type("SELL")
                .userId(user.getId())
                .mutualFundId(fund.getId())
                .units(dto.getUnits())
                .amount(amount)
                .transactionDate(LocalDate.now())
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(Authentication auth) {
        User user = getCurrentUser(auth);
        return transactionRepository.findByUserId(user.getId());
    }

    public Transaction getTransactionById(Long id, Authentication auth) {
        User user = getCurrentUser(auth);
        Transaction txn = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (!txn.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }
        return txn;
    }

    public List<PortfolioSummaryDTO> getPortfolioSummary(Authentication auth) {
        User user = getCurrentUser(auth);

        List<Transaction> transactions = transactionRepository.findByUserId(user.getId()).stream()
                .filter(txn -> txn.getType().equalsIgnoreCase("BUY"))
                .collect(Collectors.toList());

        Map<Long, List<Transaction>> grouped = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getMutualFundId));

        List<PortfolioSummaryDTO> summary = new ArrayList<>();

        for (Map.Entry<Long, List<Transaction>> entry : grouped.entrySet()) {
            Long fundId = entry.getKey();
            List<Transaction> txns = entry.getValue();

            BigDecimal totalUnits = txns.stream()
                    .map(Transaction::getUnits)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal amountInvested = txns.stream()
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            MutualFund fund = fundRepository.findById(fundId)
                    .orElseThrow(() -> new RuntimeException("Fund not found"));

            BigDecimal currentNAV = fund.getNav();
            BigDecimal currentValue = currentNAV.multiply(totalUnits).setScale(2, RoundingMode.HALF_UP);
            BigDecimal profitOrLoss = currentValue.subtract(amountInvested).setScale(2, RoundingMode.HALF_UP);

            summary.add(new PortfolioSummaryDTO(
                    fund.getFundName(),
                    fund.getFundCode(),
                    totalUnits,
                    amountInvested,
                    currentNAV,
                    currentValue,
                    profitOrLoss
            ));
        }

        return summary;
    }

    private User getCurrentUser(Authentication auth) {
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
