package com.easybytes.cards.mapper;


import com.easybytes.cards.dto.LoansDto;
import com.easybytes.cards.entity.Loans;

public class LoansMapper {
    public static LoansDto mapToCardsDto(Loans cards, LoansDto loansDto) {
        loansDto.setCardNumber(cards.getCardNumber());
        loansDto.setCardType(cards.getCardType());
        loansDto.setMobileNumber(cards.getMobileNumber());
        loansDto.setTotalLimit(cards.getTotalLimit());
        loansDto.setAvailableAmount(cards.getAvailableAmount());
        loansDto.setAmountUsed(cards.getAmountUsed());
        return loansDto;
    }

    public static Loans mapToCards(LoansDto loansDto, Loans cards) {
        cards.setCardNumber(loansDto.getCardNumber());
        cards.setCardType(loansDto.getCardType());
        cards.setMobileNumber(loansDto.getMobileNumber());
        cards.setTotalLimit(loansDto.getTotalLimit());
        cards.setAvailableAmount(loansDto.getAvailableAmount());
        cards.setAmountUsed(loansDto.getAmountUsed());
        return cards;
    }
}
