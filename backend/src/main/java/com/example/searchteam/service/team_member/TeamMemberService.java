package com.example.searchteam.service.team_member;

import com.example.searchteam.dto.request.team_member.TeamMemberRequest;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.service.domain.team_member.TeamMemberDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

/**
 * Сервис участника команды
 * @deprecated реализует методы обработки информации об участнике команды
 */
public class TeamMemberService {

    /**
     * Domain Service участника команды
     * @deprecated реализует методы обработки информации об участнике команды
     */
    private final TeamMemberDomainService service;

    /**
     * получение участника команды по id
     * @param request - id
     */
    public TeamMemberResponse getTeamMemberById(TeamMemberRequest request ){
        return service.getTeamMemberById(request.getTeamMemberId());
    }

}