package com.easybytes.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
