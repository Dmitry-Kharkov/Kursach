package com.example.searchteam.dto.request.role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class RoleRequest {
    private Long roleId;
}
