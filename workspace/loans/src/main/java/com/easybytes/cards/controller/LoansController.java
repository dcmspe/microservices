package com.easybytes.cards.controller;

import com.easybytes.cards.constants.LoansConstants;
import com.easybytes.cards.dto.LoansDto;
import com.easybytes.cards.dto.ErrorResponseDto;
import com.easybytes.cards.dto.ResponseDto;
import com.easybytes.cards.service.ILoansService;
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
        name = "CRUD REST APIS for Cards in Eazybank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account detais."
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoansService iLoansService;
    @Operation(summary = "Create Card REST API",
    description = "REST API to create new Customer and Card inside EasyBank")
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits") String mobileNumber){

        iLoansService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Card REST API",
            description = "REST API to fetch new Customer and Card details based on a mobile number")
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchCardDetails(@RequestParam @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits") String mobileNumber){
        LoansDto loansDto = iLoansService.fetchCard(mobileNumber);
        return ResponseEntity.ok(loansDto);
    }

    @Operation(summary = "Update Card REST API",
            description = "REST API to update Customer and Card details based on a account number")
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
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated = iLoansService.updateCard(loansDto);

        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Card & Customer Details REST API",
            description = "REST API to update Customer and Card details based on a account number")
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
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam @Pattern(regexp = "^|[0-9]{10}", message = "Mobile Number must be 10 digits") Long mobileNumber){
        boolean isDeleted = iLoansService.deleteCard(mobileNumber.toString());

        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

}
