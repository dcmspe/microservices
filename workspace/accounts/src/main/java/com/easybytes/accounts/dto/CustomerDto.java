package com.easybytes.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;
}
