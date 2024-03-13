package com.example.searchteam.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ResetPasswordRequest {
    private UUID code;
    private String password;

}
