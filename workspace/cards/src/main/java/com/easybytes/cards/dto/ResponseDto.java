package com.easybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    name="Response",
    description="Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(description = "State code in the response")
    private String statusCode;

    @Schema(description = "Status message in the response")
    private String statusMessage;

}
