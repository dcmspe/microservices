package com.easybytes.accounts.mapper;

import com.easybytes.accounts.dto.CardsDto;
import com.easybytes.accounts.entity.Customer;

public class CustomerMapper {
    public static CardsDto mapToCustomerDto(Customer customer, CardsDto cardsDto){
        cardsDto.setName(customer.getName());
        cardsDto.setEmail(customer.getEmail());
        cardsDto.setMobileNumber(customer.getMobileNumber());

        return cardsDto;
    }

    public static Customer mapToCustomer(CardsDto cardsDto, Customer customer){
        customer.setName(cardsDto.getName());
        customer.setEmail(cardsDto.getEmail());
        customer.setMobileNumber(cardsDto.getMobileNumber());

        return customer;
    }
}
