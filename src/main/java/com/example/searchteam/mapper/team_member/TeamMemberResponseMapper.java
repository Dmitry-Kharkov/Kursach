package com.example.searchteam.mapper.team_member;

import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberResponseMapper implements Mapper<TeamMemberResponse, TeamMember> {
    private final TeamMemberTypeResponseMapper teamMemberTypeResponseMapper;

    @Override
    public TeamMemberResponse from(TeamMember source) {
        return new TeamMemberResponse()
                .setTeamMemberId(source.getId())
                .setName(source.getName())
                .setTeamMemberType(teamMemberTypeResponseMapper.from(source.getTeamMemberType()))
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
