package com.example.searchteam.dto.request.team;

import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TeamFiltrationRequest {

    private List<TypeTeamResponse> teamTypes;
    private boolean isCompleted;
    private String name;
    private LocalDateTime start;
    private LocalDateTime finish;
    private List<UserResponse> users;
    private List<TeamMemberAddRequest> members = Collections.emptyList();

    public boolean getIsCompleted(){
        return isCompleted;
    }

}