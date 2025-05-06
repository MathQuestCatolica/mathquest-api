package com.mathquest.mathquest.shared.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseDTO {

    @JsonProperty("statusCode")
    @JsonAlias("status_code")
    private String statusCode;

    @JsonProperty("statusMessage")
    @JsonAlias("status_message")
    private String statusMessage;

    private LocalDateTime timestamp;

    private String details;

    @JsonIgnore
    public void setMessagesApiProperties(MessagesApiProperties messagesProperties, Object... args) {
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
        this.statusCode = messagesProperties.name();
        this.statusMessage = MessagesApiSource.getInstance().message(messagesProperties.name(), args);
    }

    @JsonIgnore
    public void setMessagesApiProperties(MessagesApiProperties messagesProperties) {
        this.setMessagesApiProperties(messagesProperties, null);
    }

    @JsonIgnore
    public void setMessageErrorHttp(String statusCode, String statusMessage) {
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    @JsonIgnore
    public static ResponseDTO with(MessagesApiProperties messagesApiProperties, Object[] args) {
        var response = new ResponseDTO();
        response.setMessagesApiProperties(messagesApiProperties, args);
        return response;
    }

    @JsonIgnore
    public static ResponseDTO with(MessagesApiProperties messagesApiProperties) {
        return with(messagesApiProperties, null);
    }

    @JsonIgnore
    public static ResponseDTO ok() {
        var response = new ResponseDTO();
        response.setMessagesApiProperties(MessagesApiProperties.S001);
        return response;
    }

    @JsonIgnore
    public static ResponseDTO error(HttpStatus httpStatus) {
        var response = new ResponseDTO();
        response.setMessageErrorHttp(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
        return response;
    }
}
