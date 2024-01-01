package com.example.searchteam.mapper.team_member;

import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberTypeResponseMapper implements Mapper<TeamMemberTypeResponse, TeamMemberType> {
    @Override
    public TeamMemberTypeResponse from(TeamMemberType source) {
        return new TeamMemberTypeResponse()
                .setTeamMemberTypeId(source.getId())
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
