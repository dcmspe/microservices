package com.easybytes.loans.service;

import com.easybytes.loans.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber
     */
    void createLoan(String mobileNumber);


    LoansDto fetchLoan(String mobileNumber);

    /**
     /* *
     *
     * @param loansDto
     * @return
     */
    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteLoan(String mobileNumber);
}
