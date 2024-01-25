package com.example.searchteam.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FiltrationUser {
    private String login;
    private Long from;
    private Long count;
    private String mean;
}
