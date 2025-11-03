package com.easybytes.cards.service;

import com.easybytes.cards.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber
     */
    void createLoan(String mobileNumber);


    LoansDto fetchCard(String mobileNumber);

    /**
     /* *
     *
     * @param loansDto
     * @return
     */
    boolean updateCard(LoansDto loansDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteCard(String mobileNumber);
}
