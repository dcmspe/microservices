package com.easybytes.accounts.service;

import com.easybytes.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);


    CustomerDto fetchAccount(String mobileNumber);

    /**
     /* *
     *
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);
}
