package com.easybytes.accounts.service;

import com.easybytes.accounts.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param cardsDto
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
    boolean deleteAccount(String mobileNumber);
}
