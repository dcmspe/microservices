package com.easybytes.accounts.service.impl;

import com.easybytes.accounts.constants.CardsConstants;
import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.dto.CardsDto;
import com.easybytes.accounts.entity.Cards;
import com.easybytes.accounts.entity.Customer;
import com.easybytes.accounts.exception.CardAlreadyExistsException;
import com.easybytes.accounts.exception.ResourceNotFoundException;
import com.easybytes.accounts.mapper.AccountsMapper;
import com.easybytes.accounts.mapper.CustomerMapper;
import com.easybytes.accounts.repository.CardsRepository;
import com.easybytes.accounts.repository.CustomerRepository;
import com.easybytes.accounts.service.ICardsService;
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
     * Creates a new account for the provided customer details.
     *
     * @param cardsDto an object containing customer information such as name, email, and mobile number
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber)
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException(MessageFormat.format("Card already exists with given mobile number {0}", mobileNumber));
        }
        cardsRepository.save(createNewCard(mobileNumber));

    }

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
    public CardsDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Cards accounts = cardsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CardsDto cardsDto = CustomerMapper.mapToCustomerDto(customer, new CardsDto());
        cardsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return cardsDto;
    }

    @Override
    public boolean updateAccount(CardsDto cardsDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = cardsDto.getAccountsDto();

        if(accountsDto != null){
            Cards accounts = cardsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            cardsRepository.save(accounts);

            Customer customer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "customerId", accounts.getCustomerId().toString())
            );

            CustomerMapper.mapToCustomer(cardsDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }
        return isUpdated;

    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        cardsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

    private Cards createNewAccount(Customer customer){
        Cards newAccount = new Cards();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = (long) (Math.random() * 1000000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(CardsConstants.SAVINGS);
        newAccount.setBranchAddress(CardsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
