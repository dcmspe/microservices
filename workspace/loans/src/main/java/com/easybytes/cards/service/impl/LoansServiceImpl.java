package com.easybytes.cards.service.impl;

import com.easybytes.cards.exception.ResourceNotFoundException;
import com.easybytes.cards.repository.LoansRepository;
import com.easybytes.cards.constants.LoansConstants;
import com.easybytes.cards.dto.LoansDto;
import com.easybytes.cards.entity.Loans;
import com.easybytes.cards.exception.LoanAlreadyExistsException;
import com.easybytes.cards.mapper.LoansMapper;
import com.easybytes.cards.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Loans> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new LoanAlreadyExistsException(MessageFormat.format("Card already exists with given mobile number {0}", mobileNumber));
        }
        cardsRepository.save(createNewCard(mobileNumber));

    }

    /**
     *
     * @param mobileNumber - Mobile number of the customer
     * @return the new card details
     */
    private Loans createNewCard(String mobileNumber){
        Loans newCard = new Loans();
        long randomCardNumber = (long) (Math.random() * 100000000000L);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(LoansConstants.CREDIT_CARD);
        newCard.setTotalLimit(LoansConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(LoansConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public LoansDto fetchCard(String mobileNumber) {

        Loans cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return LoansMapper.mapToCardsDto(cards, new LoansDto());
    }

    @Override
    public boolean updateCard(LoansDto loansDto) {
        Loans cards = cardsRepository.findByCardNumber(loansDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", loansDto.getCardNumber())
        );

        LoansMapper.mapToCards(loansDto, cards);
        cardsRepository.save(cards);

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {

        Loans card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)
        );

        cardsRepository.deleteById(card.getCardId());

        return true;
    }
}
