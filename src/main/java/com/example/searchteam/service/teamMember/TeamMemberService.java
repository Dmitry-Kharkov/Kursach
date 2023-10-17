package com.example.searchteam.service.teamMember;

import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.request.teamMember.TeamMemberRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.teamMember.TeamMemberResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import com.example.searchteam.service.domain.teamMember.TeamMemberDomainService;
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