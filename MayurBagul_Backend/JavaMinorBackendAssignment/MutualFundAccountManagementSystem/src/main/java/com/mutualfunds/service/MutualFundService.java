package com.mutualfunds.service;

import com.mutualfunds.dto.MutualFundDTO;
import com.mutualfunds.model.MutualFund;
import com.mutualfunds.repository.MutualFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MutualFundService {

    private final MutualFundRepository mutualFundRepository;

    public MutualFund addFund(MutualFundDTO dto) {
        if (mutualFundRepository.existsByFundCode(dto.getFundCode())) {
            throw new RuntimeException("Fund code already exists.");
        }

        MutualFund fund = MutualFund.builder()
                .fundName(dto.getFundName())
                .fundCode(dto.getFundCode())
                .category(dto.getCategory())
                .nav(dto.getNav())
                .lastUpdated(LocalDate.now())
                .build();

        return mutualFundRepository.save(fund);
    }

    public List<MutualFund> getAllFunds() {
        return mutualFundRepository.findAll();
    }

    public MutualFund getFundById(Long id) {
        return mutualFundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mutual Fund not found"));
    }
}
