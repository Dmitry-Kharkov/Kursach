package com.example.searchteam.dto.request.role;

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
    //private RoleTypeResponse roleType;
    private Long roleTypeId;
    private String description;

}
