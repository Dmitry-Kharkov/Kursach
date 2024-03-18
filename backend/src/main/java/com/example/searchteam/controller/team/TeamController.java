package com.example.searchteam.controller.team;

import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team.TeamFiltrationRequest;
import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.response.team.ShortTeamResponse;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.service.team.TeamService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
/**
 * Контроллер для команды
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Управление командами",
                description = "Контроллер управления командами", version = "1.0.0"
        )
)
public class TeamController {

    public static final String TEAM_GET_BY_ID = "/api/v1/team/get-by-id";
    public static final String TEAM_GET_ALL = "/api/v1/team/get-all";
    public static final String TEAM_ADD = "/api/v1/team/add";
    public static final String TEAM_EDIT = "/api/v1/team/edit";
    public static final String TEAM_DELETE = "/api/v1/team/delete";
    public static final String TEAM_SEARCH="/api/v1/team/search";


    private final TeamService service;

    /**
     * Запрос получения команды по id
     * @param request - teamId
     * @return команда
     */
    @Operation(summary="Получение команды по ID")
    @PostMapping(
            value = TEAM_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse getTeamById(@RequestBody TeamRequest request){
        return service.getTeamById(request);
    }

    /**
     * Запрос получения всех команд
     * @return список команд
     */
    @Operation(summary="Получение всех команд")
    @PostMapping(
            value = TEAM_GET_ALL,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<TeamResponse> getAllTeams(){
        return service.getAllTeams();
    }

    /**
     * Запрос добавления команды
     * @param request - TeamAddRequest(id,name,description,userId,typeTeamId,members)
     * @return команда
     */
    @Operation(summary="Создание команды")
    @PostMapping(
            value = TEAM_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse addTeam(@RequestBody TeamAddRequest request) {
        return service.addTeam(request);
    }

    /**
     * Запрос изменения команды
     * @param request - TeamAddRequest(id,name,description,userId,typeTeamId,members)
     * @return команда
     */
    @Operation(summary="Изменение команды")
    @PostMapping(
            value = TEAM_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public TeamResponse editTeam(@RequestBody TeamAddRequest request) {
        return service.editTeam(request);
    }

    /**
     * Запрос удаления команды
     * @param request - TeamRequest(teamId)
     */
    @Operation(summary="Удаление команды")
    @PostMapping(
            value = TEAM_DELETE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void deleteTeam(@RequestBody TeamRequest request) {
        service.deleteTeam(request);
    }

    /**
     * Запрос поиска команды
     * @param request - TeamFiltrationRequest(teamTypes,isCompleted,name,start,finish,users,members)
     * @return укороченный список команд
     */
    @Operation(summary="Поиск команды")
    @PostMapping(
            value = TEAM_SEARCH,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<ShortTeamResponse> getSearchTeams(@RequestBody TeamFiltrationRequest request){
        return service.getSearchTeams(request);
    }


}
