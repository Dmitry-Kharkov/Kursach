package com.example.searchteam.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)

public class UserEditRolesRequest {

    private Long id;
    private List<Long> roles;

}
