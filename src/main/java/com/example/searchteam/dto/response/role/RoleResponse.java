package com.example.searchteam.dto.response.role;

import com.example.searchteam.domain.role.RoleTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class RoleResponse {

    private Long roleId;
    private RoleTypeEnum roleType;
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;

}
