package com.mutualfunds.scheduler;

import com.mutualfunds.model.MutualFund;
import com.mutualfunds.repository.MutualFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class NAVUpdateScheduler {

    private final MutualFundRepository mutualFundRepository;
    private final Random random = new Random();

    // Runs every 24 hours: @Scheduled(cron = "0 0 0 * * *")
    // For demo/testing: every 30 seconds
    @Scheduled(fixedRate = 30000) // 30 seconds (demo)
    public void updateNAVs() {
        List<MutualFund> funds = mutualFundRepository.findAll();

        for (MutualFund fund : funds) {
            BigDecimal currentNav = fund.getNav();

            // Random % change between -2% and +2%
            double percentChange = -2 + (4 * random.nextDouble());
            BigDecimal changeFactor = BigDecimal.valueOf(1 + percentChange / 100);

            BigDecimal newNav = currentNav.multiply(changeFactor).setScale(2, RoundingMode.HALF_UP);
            fund.setNav(newNav);
            fund.setLastUpdated(LocalDate.now());
        }

        mutualFundRepository.saveAll(funds);
        System.out.println("✅ NAVs updated at: " + LocalDate.now());
    }
}
