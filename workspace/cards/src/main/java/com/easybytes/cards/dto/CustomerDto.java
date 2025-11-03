package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "Alfred Robert"
    )
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "alfred.robert@email.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email is not valid")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(
      description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
