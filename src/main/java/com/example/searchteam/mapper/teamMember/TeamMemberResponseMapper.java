package com.example.searchteam.mapper.teamMember;

import com.example.searchteam.domain.teamMember.TeamMember;
import com.example.searchteam.dto.response.teamMember.TeamMemberResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberResponseMapper implements Mapper<TeamMemberResponse, TeamMember> {
    @Override
    public TeamMemberResponse from(TeamMember source) {
        return new TeamMemberResponse()
                .setTeamMemberId(source.getId())
                .setName(source.getName())
                .setTeamMemberTypeId(source.getTeamMemberType().getId())
                .setTeamId(source.getTeam().getId())
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
