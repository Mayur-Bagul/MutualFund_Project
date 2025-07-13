package com.mutualfunds.repository;

import com.mutualfunds.model.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MutualFundRepository extends JpaRepository<MutualFund, Long> {
    Optional<MutualFund> findByFundCode(String fundCode);
    boolean existsByFundCode(String fundCode);
}
