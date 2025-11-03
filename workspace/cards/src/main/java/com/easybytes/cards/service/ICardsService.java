package com.easybytes.cards.service;

import com.easybytes.cards.dto.CardsDto;

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
