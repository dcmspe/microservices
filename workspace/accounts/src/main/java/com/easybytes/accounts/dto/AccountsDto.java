package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
        description = "Account number of Easy Bank account",
        example = "3214567890"
    )
    @NotEmpty(message = "Account Number can not be null or empty")
    @Pattern(regexp = "^|[0-9]{10}", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type of Easy Bank account"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address",
            example = "123 Easy Bank Street, Vancouver, BC"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
