package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMapper implements Mapper<Team, TeamAddRequest> {

    private final TeamMemberMapper teamMemberMapper;

    @Override
    public Team from(TeamAddRequest source) {
        var team = new Team()
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setUser(new User(source.getUserId()))
                .setTeamType(new TeamType(source.getTypeTeamId()))
                .setCompleted(false);

        var members = teamMemberMapper.from(source.getMembers());
        members.forEach(m -> m.setTeam(team));
        team.setTeamMembers(members);
        return team;
    }
}
