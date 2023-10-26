package com.example.searchteam.dto.response.teamMember;

import com.example.searchteam.dto.response.team.TeamResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamMemberResponse {

    private Long teamMemberId;
    private String name;
    private TeamMemberTypeResponse teamMemberType;
    private TeamResponse team;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;

}
