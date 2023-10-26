package com.example.searchteam.mapper.teamMember;

import com.example.searchteam.domain.teamMember.TeamMemberType;
import com.example.searchteam.dto.response.teamMember.TeamMemberTypeResponse;
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
