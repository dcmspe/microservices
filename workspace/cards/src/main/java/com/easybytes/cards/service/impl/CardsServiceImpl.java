package com.easybytes.cards.service.impl;

import com.easybytes.cards.exception.ResourceNotFoundException;
import com.easybytes.cards.repository.CardsRepository;
import com.easybytes.cards.constants.CardsConstants;
import com.easybytes.cards.dto.CardsDto;
import com.easybytes.cards.entity.Cards;
import com.easybytes.cards.exception.CardAlreadyExistsException;
import com.easybytes.cards.mapper.CardsMapper;
import com.easybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException(MessageFormat.format("Card already exists with given mobile number {0}", mobileNumber));
        }
        cardsRepository.save(createNewCard(mobileNumber));

    }

    /**
     *
     * @param mobileNumber - Mobile number of the customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = (long) (Math.random() * 100000000000L);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber())
        );

        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {

        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)
        );

        cardsRepository.deleteById(card.getCardId());

        return true;
    }
}
