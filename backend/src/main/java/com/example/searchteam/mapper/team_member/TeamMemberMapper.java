package com.example.searchteam.mapper.team_member;

import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberMapper implements Mapper<TeamMember, TeamMemberAddRequest> {
    @Override
    public TeamMember from(TeamMemberAddRequest source) {
        return new TeamMember()
                .setTeamMemberType(new TeamMemberType(source.getTypeId()))
                .setName(source.getName())
                .setDescription(source.getDescription());
    }
}
