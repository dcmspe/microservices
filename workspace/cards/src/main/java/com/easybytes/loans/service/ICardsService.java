package com.easybytes.loans.service;

import com.easybytes.loans.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber
     */
    void createCard(String mobileNumber);


    CardsDto fetchCard(String mobileNumber);

    /**
     /* *
     *
     * @param cardsDto
     * @return
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteCard(String mobileNumber);
}
