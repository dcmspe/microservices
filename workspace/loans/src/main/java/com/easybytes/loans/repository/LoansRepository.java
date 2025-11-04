package com.easybytes.loans.repository;

import com.easybytes.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByLoanNumber(String loanNumber);

    Optional<Loans> findByMobileNumber(String mobileNumber);
}
