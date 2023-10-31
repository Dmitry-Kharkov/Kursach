package com.example.searchteam.dto.response.team_member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamMemberTypeResponse {

    private Long teamMemberTypeId;
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;

}
