package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.team_member.TeamMemberResponseMapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamResponseMapper implements Mapper<TeamResponse, Team> {
    private final UserResponseMapper userResponseMapper;
    private final TeamTypeResponseMapper teamTypeResponseMapper;
    private final TeamMemberResponseMapper teamMemberResponseMapper;
    @Override
    public TeamResponse from(Team source) {
        return new TeamResponse()
                .setTeamId(source.getId())
                .setName(source.getName())
                .setUser(userResponseMapper.from(source.getUser()))
                .setDescription(source.getDescription())
                .setTypeTeam(teamTypeResponseMapper.from(source.getTeamType()))
                .setMembers(teamMemberResponseMapper.from(source.getTeamMembers()))
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
