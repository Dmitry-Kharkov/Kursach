package com.example.searchteam.dto.response.team;

import com.example.searchteam.dto.response.teamMember.TeamMemberTypeResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import liquibase.changelog.ChangeSet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TypeTeamResponse {

    private Long typeTeamId;
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
}