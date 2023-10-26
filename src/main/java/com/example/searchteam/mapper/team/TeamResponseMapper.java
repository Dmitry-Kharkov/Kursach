package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TeamResponseMapper implements Mapper<TeamResponse, Team> {
    private final UserResponseMapper userResponseMapper;
    private final TeamTypeResponseMapper teamTypeResponseMapper;
    @Override
    public TeamResponse from(Team source) {
        return new TeamResponse()
                .setTeamId(source.getId())
                .setName(source.getName())
                .setUser(userResponseMapper.from(source.getUser()))
                .setDescription(source.getDescription())
                .setTypeTeam(teamTypeResponseMapper.from(source.getTeamType()))
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
