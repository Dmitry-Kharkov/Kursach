package com.example.searchteam.dto.request.team;

import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TeamAddRequest {

    private Long teamId;
    private String name;
    private String description;
    private Long userId;
    private Long typeTeamId;
    private List<TeamMemberAddRequest> members = Collections.emptyList();


}
