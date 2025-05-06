package com.mathquest.mathquest.shared.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseDTO<T> {

    @JsonProperty("statusCode")
    @JsonAlias("status_code")
    private Integer statusCode;

    @JsonProperty("statusMessage")
    @JsonAlias("status_message")
    private String statusMessage;

    private LocalDateTime timestamp;

    @JsonInclude()
    private T data;
}
