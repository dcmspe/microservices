package com.easybytes.loans.controller;

import com.easybytes.loans.constants.LoansConstants;
import com.easybytes.loans.dto.LoansDto;
import com.easybytes.loans.dto.ErrorResponseDto;
import com.easybytes.loans.dto.ResponseDto;
import com.easybytes.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST APIS for Loans in Eazybank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE Loan detais."
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService iLoansService;
    @Operation(summary = "Create Loan REST API",
    description = "REST API to create new Loan inside EasyBank")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@Valid @RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber){

        iLoansService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Loan REST API",
            description = "REST API to fetch new Loan based on a mobile number")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits") String mobileNumber){
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.ok(loansDto);
    }

    @Operation(summary = "Update Card REST API",
            description = "REST API to update loan")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "HTTP Status EXPECTATION FAILED"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server ERROR"
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated = iLoansService.updateLoan(loansDto);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Loan Details REST API",
            description = "REST API to update Loan and  based on mobile number")
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION FAILED"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server ERROR",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits") String mobileNumber){
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

}
