package com.example.searchteam.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)

public class UserAddRequest {

    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;

}
