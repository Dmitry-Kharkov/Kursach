package com.example.searchteam.dto.response.team;

import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamResponse {

    private Long teamId;
    private String name;
    private UserResponse user;
    private String description;
    private TypeTeamResponse typeTeam;
    private List<TeamMemberResponse> members = Collections.emptyList();
    private LocalDateTime created;
    private LocalDateTime modified;

}


