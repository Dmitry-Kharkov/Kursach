package com.example.searchteam.service.team_member;

import com.example.searchteam.dto.request.team_member.TeamMemberRequest;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.service.domain.team_member.TeamMemberDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberDomainService service;

    public TeamMemberResponse getTeamMemberById(TeamMemberRequest request ){
        return service.getTeamMemberById(request.getTeamMemberId());
    }

}