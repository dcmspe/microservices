package com.easybytes.accounts.service.impl;

import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.repository.AccountsRepository;
import com.easybytes.accounts.repository.CustomerRepository;
import com.easybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new account for the provided customer details.
     *
     * @param customerDto an object containing customer information such as name, email, and mobile number
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
