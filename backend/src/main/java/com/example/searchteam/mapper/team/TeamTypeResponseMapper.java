package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamTypeResponseMapper implements Mapper<TypeTeamResponse, TeamType> {
    @Override
    public TypeTeamResponse from(TeamType source) {
        return new TypeTeamResponse()
                .setTypeTeamId(source.getId())
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}