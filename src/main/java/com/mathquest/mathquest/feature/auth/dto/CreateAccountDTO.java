package com.mathquest.mathquest.feature.auth.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateAccountDTO {
    String username;
    String password;
}
