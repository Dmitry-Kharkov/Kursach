package com.example.searchteam.dto.request.role;

import com.example.searchteam.domain.role.RoleTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)

public class RoleAddRequest {

    private Long id;
    private String name;
    private RoleTypeEnum roleType;
    private String description;

}
