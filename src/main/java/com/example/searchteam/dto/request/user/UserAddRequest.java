package com.example.searchteam.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)

public class UserAddRequest {

    private Long id;
    private String name;
    private String login;
    private String password;

    public boolean verificationPassword() {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern PASSWORD_PATTERN = Pattern.compile(regex);
        Matcher m = PASSWORD_PATTERN.matcher(password);
        return (m.matches());
    }
}
