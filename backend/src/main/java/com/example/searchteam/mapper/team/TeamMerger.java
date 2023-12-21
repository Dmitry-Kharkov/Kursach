package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.mapper.Merger;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMerger implements Merger<Team, TeamAddRequest> {
    private final TeamMemberMapper teamMemberMapper;

    @Override
    public Team merge(Team target, TeamAddRequest source) {
        var members = teamMemberMapper.from(source.getMembers());
        members.forEach(m -> m.setTeam(target));
        target.setTeamMembers(members);

        return target
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setTeamType(new TeamType(source.getTypeTeamId()))
                .setUser(new User(source.getUserId()));

    }
}