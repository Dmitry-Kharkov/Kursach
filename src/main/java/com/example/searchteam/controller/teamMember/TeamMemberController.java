package com.example.searchteam.controller.teamMember;

import com.example.searchteam.dto.request.teamMember.TeamMemberRequest;
import com.example.searchteam.dto.response.teamMember.TeamMemberResponse;
import com.example.searchteam.service.teamMember.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TeamMemberController {

    public static final String TEAM_MEMBER_GET_BY_ID = "/api/v1/team-member/get-by-id";

    private final TeamMemberService service;

    @PostMapping(
            value = TEAM_MEMBER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamMemberResponse getTeamMemberById(@RequestBody TeamMemberRequest request){
        return service.getTeamMemberById(request);
    }


}