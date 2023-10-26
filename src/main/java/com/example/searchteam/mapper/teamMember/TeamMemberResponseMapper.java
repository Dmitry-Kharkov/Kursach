package com.example.searchteam.mapper.teamMember;

import com.example.searchteam.domain.teamMember.TeamMember;
import com.example.searchteam.dto.response.teamMember.TeamMemberResponse;
import com.example.searchteam.dto.response.teamMember.TeamMemberTypeResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.team.TeamResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberResponseMapper implements Mapper<TeamMemberResponse, TeamMember> {
    private TeamMemberTypeResponseMapper teamMemberTypeResponseMapper;
    private TeamResponseMapper teamResponseMapper;

    @Override
    public TeamMemberResponse from(TeamMember source) {
        return new TeamMemberResponse()
                .setTeamMemberId(source.getId())
                .setName(source.getName())
                .setTeamMemberType(teamMemberTypeResponseMapper.from(source.getTeamMemberType()))
                .setTeam(teamResponseMapper.from(source.getTeam()))
                .setDescription(source.getDescription())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
