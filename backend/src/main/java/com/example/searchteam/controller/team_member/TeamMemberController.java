package com.example.searchteam.controller.team_member;

import com.example.searchteam.dto.request.team_member.TeamMemberRequest;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.service.team_member.TeamMemberService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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

@OpenAPIDefinition(
        info = @Info(
                title = "Управление участниками команды",
                description = "Контроллер управления участниками команды", version = "1.0.0"
        )
)
public class TeamMemberController {

    public static final String TEAM_MEMBER_GET_BY_ID = "/api/v1/team-member/get-by-id";

    private final TeamMemberService service;

    /**
     * Запрос получения участника команды по id
     * @param request - TeamMemberId
     * @return участник команды
     */
    @Operation(summary = "Получение участника команды по ID")
    @PostMapping(
            value = TEAM_MEMBER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamMemberResponse getTeamMemberById(@RequestBody TeamMemberRequest request){
        return service.getTeamMemberById(request);
    }

}