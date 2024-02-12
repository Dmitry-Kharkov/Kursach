package com.example.searchteam.controller.team_member;

import com.example.searchteam.dto.request.team_member.TeamMemberRequest;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.service.team_member.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor

/**
 * Контроллер для участника команды
 */
public class TeamMemberController {

    public static final String TEAM_MEMBER_GET_BY_ID = "/api/v1/team-member/get-by-id";

    private final TeamMemberService service;

    /**
     * Запрос получения участника команды по id
     * @param request - TeamMemberId
     */
    @PostMapping(
            value = TEAM_MEMBER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamMemberResponse getTeamMemberById(@RequestBody TeamMemberRequest request){
        return service.getTeamMemberById(request);
    }

}