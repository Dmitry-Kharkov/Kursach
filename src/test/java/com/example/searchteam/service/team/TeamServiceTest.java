package com.example.searchteam.service.team;


import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team.TeamFiltrationRequest;
import com.example.searchteam.dto.request.team.TeamRequest;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.dto.response.applicant.ShortApplicantResponse;
import com.example.searchteam.dto.response.team.ShortTeamResponse;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.team.TeamDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Long USER_ID=0l;
    private static final UserResponse USER=new UserResponse().setFullName("TEST");
    private static final TypeTeamResponse TYPE_TEAM=new TypeTeamResponse().setName("TEST");
    private static final List<String> TYPE_TEAM_STRING=List.of("TEST","TEST");
    private static final List<String> USERS_STRING=List.of("TEST","TEST");
    private static final List<String> MEMBERS_STRING=List.of("TEST","TEST");
    private static final Long TYPE_TEAM_ID=0L;
    private static final List<TeamMemberAddRequest> MEMBERS=null;
    private static final List<TeamMemberResponse> MEMBERS_RES=List.of(new TeamMemberResponse().setTeamMemberId(ID).setName(NAME));
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamDomainService domainService;

    @InjectMocks
    private TeamService service;

    @Test
    void getTeamByIdTest() {
        when(domainService.getTeamById(any())).thenReturn(getTeamResponse());

        var result = service.getTeamById(getTeamRequest());

        assertEquals(ID, result.getTeamId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getTeamById(any());
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void getAllTeamsTest() {
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result = service.getAllTeams();
        for(TeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(USER, e.getUser());
            assertEquals(TYPE_TEAM, e.getTypeTeam());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void addTeamTest() {

        when(domainService.getTeamById(any())).thenReturn(getTeamResponse());
        when(domainService.addTeam(any())).thenReturn(ID);

        var result = service.addTeam(getTeamAddRequest());

        assertEquals(ID, result.getTeamId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getTeamById(any());
        verify(domainService).addTeam(any());
        verifyNoMoreInteractions(domainService);

    }


    @Test
    void editTeamTest() {
        when(domainService.editTeam(any())).thenReturn(getTeamResponse().getTeamId());
        when(domainService.getTeamById(any())).thenReturn(getTeamResponse());

        var result = service.editTeam(getTeamAddRequest());

        assertEquals(ID, result.getTeamId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editTeam(any());
        verify(domainService).getTeamById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void deleteTeamTest() {
        service.deleteTeam(getTeamRequest());
        verify(domainService).deleteTeamById(any());
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void applyFilterTestNotInfo(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result=service.getSearchTeams(getTeamFiltrationRequestNotInfo());
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void applyFilterTestDataFinish(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result=service.getSearchTeams(getTeamFiltrationRequestNotInfo().setFinish(CREATED.plusMinutes(-1)));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void applyFilterTestDataStart(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result=service.getSearchTeams(getTeamFiltrationRequestNotInfo().setStart(MODIFIED));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void applyFilterTestComleted(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result= service.getSearchTeams(getTeamFiltrationRequestNotInfo().setIsCompleted(true));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void applyFilterTestName(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result= service.getSearchTeams(getTeamFiltrationRequestNotInfo().setName(NAME+"1"));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void applyFilterTestTeamType(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result=service.getSearchTeams(getTeamFiltrationRequestNotInfo().setTeamTypes(List.of(DESCRIPTION)));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void applyFilterTestUsers(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result= service.getSearchTeams(getTeamFiltrationRequestNotInfo().setUsers(List.of(DESCRIPTION)));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void applyFilterTestMembers(){
        when(domainService.getAllTeams()).thenReturn(List.of(getTeamResponse(),getTeamResponse(),getTeamResponse()));
        var result=service.getSearchTeams(getTeamFiltrationRequestNotInfo().setMembers(List.of(NAME)));
        for(ShortTeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllTeams();
        verifyNoMoreInteractions(domainService);
    }

    private TeamRequest getTeamRequest() {
        return new TeamRequest()
                .setTeamId(ID);
    }

    private TeamFiltrationRequest getTeamFiltrationRequest() {
        return new TeamFiltrationRequest()
                .setStart(CREATED)
                .setFinish(MODIFIED)
                .setTeamTypes(TYPE_TEAM_STRING)
                .setUsers(USERS_STRING)
                .setMembers(MEMBERS_STRING)
                .setIsCompleted(false)
                .setName(NAME);
    }

    private TeamResponse getTeamResponse() {
        return new TeamResponse()
                .setTeamId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setUser(USER)
                .setMembers(MEMBERS_RES)
                .setTypeTeam(TYPE_TEAM)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private TeamAddRequest getTeamAddRequest() {
        return new TeamAddRequest()
                .setId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTypeTeamId(TYPE_TEAM_ID)
                .setUserId(USER_ID)
                .setMembers(MEMBERS)
                .setId(ID);
    }

    private TeamFiltrationRequest getTeamFiltrationRequestNotInfo(){
        return new TeamFiltrationRequest();
    }



}