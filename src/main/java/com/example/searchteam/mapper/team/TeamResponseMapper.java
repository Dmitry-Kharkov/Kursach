package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.mapper.Mapper;
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
    @Override
    public TeamResponse from(Team source) {
        return new TeamResponse()
                .setTeamId(source.getId())
                .setName(source.getName())
                .setUserId(source.getUser().getId())
                .setDescription(source.getDescription())
                .setTypeTeamId(source.getTeamType().getId())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
