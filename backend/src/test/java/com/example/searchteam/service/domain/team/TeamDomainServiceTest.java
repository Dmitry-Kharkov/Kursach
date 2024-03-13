package com.example.searchteam.service.domain.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.dto.response.team.TeamResponse;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.team.TeamMapper;
import com.example.searchteam.mapper.team.TeamMerger;
import com.example.searchteam.mapper.team.TeamResponseMapper;
import com.example.searchteam.repository.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final TypeTeamResponse TEAM_TYPE_RESPONSE = null;
    private static final TeamType TEAM_TYPE = null;
    private static final User USER = null;
    private static final UserResponse USER_RESPONSE = null;
    private static final List<TeamMemberAddRequest> MEMBERS = List.of(new TeamMemberAddRequest().setTeamMemberId(ID).setName(NAME));
    private static final List<TeamMemberResponse> MEMBERS_RESPONSE = null;
    private static final List<TeamMember> TEAM_MEMBERS = null;
    private static final Long TEAM_TYPE_ID = 0L;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamRepository repository;
    @Mock
    private TeamResponseMapper responseMapper;
    @Mock
    private TeamMerger merger;
    @Mock
    private TeamMapper mapper;


    @InjectMocks
    private TeamDomainService domainService;

    @Test
    void getTeamByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(getTeam()));
      //  when(responseMapper.from((Team)any())).thenReturn(getTeamResponse());

        var result = domainService.getTeamById(any());

        assertEquals(ID, result.getTeamId());
        assertEquals(NAME, result.getName());
       // assertEquals(TEAM_TYPE, result.getTypeTeam());
        assertNull(result.getMembers());
      //  assertEquals(USER, result.getUser());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(repository).findById(any());
        //verify(responseMapper).from((Team)any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(responseMapper);
    }

    @Test
    void getTeamByNameTest() {
        when(repository.getTeamByName(any())).thenReturn(List.of(getTeam(),getTeam(),getTeam()));

        var result = domainService.getTeamByName(any());

        for(Team e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamType());
            assertNull(e.getTeamMembers());
            assertEquals(USER, e.getUser());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamByName(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getTeamByUserIdTest() {
        when(repository.getTeamByUserId(any())).thenReturn(List.of(getTeam(),getTeam(),getTeam()));

        var result = domainService.getTeamByUserId(any());

        for(Team e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamType());
            assertNull(e.getTeamMembers());
            assertEquals(USER, e.getUser());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamByUserId(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getTeamByTeamTypeIdTest() {
        when(repository.getTeamByUserId(any())).thenReturn(List.of(getTeam(),getTeam(),getTeam()));

        var result = domainService.getTeamByTeamTypeId(any());

        for(Team e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamType());
            assertNull(e.getTeamMembers());
            assertEquals(USER, e.getUser());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamByUserId(any());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void getAllTeamsTest() {
        when(repository.findAll()).thenReturn(List.of(getTeam(),getTeam(),getTeam()));
        var result = domainService.getAllTeams();
        for(TeamResponse e:result) {
            assertEquals(ID, e.getTeamId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTypeTeam());
            assertEquals(USER, e.getUser());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addTeamTest() {

        when(repository.save(any())).thenReturn(getTeam());
        when(mapper.from((TeamAddRequest)any())).thenReturn(getTeam());
        //when(repository.addTeam(any())).thenReturn(ID);

        var result = domainService.addTeam(getTeamAddRequest());

        assertEquals(ID, result);

        //verify(domainService).getTeamById(any());
        verify(repository).save(any());
        verify(mapper).from((TeamAddRequest)any());
        verifyNoMoreInteractions(repository);

    }


    @Test
    void editTeamTest() {
        when(repository.save(any())).thenReturn(getTeam());
        when(repository.getReferenceById(any())).thenReturn(getTeam());
        when(merger.merge(any(),any())).thenReturn(getTeam());
        var result = domainService.editTeam(getTeamAddRequest());

        assertEquals(ID, result);

        verify(repository).save(any());
        verify(repository).getReferenceById(any());
        verify(merger).merge(any(),any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteTeamByIdTest() {
        domainService.deleteTeamById(ID);
        verify(repository).deleteById(any());
        verifyNoMoreInteractions(repository);
    }
    @Test
    void deleteTeamByNameTest() {
        domainService.deleteTeamByName(NAME);
        verify(repository).deleteTeamByName(any());
        verifyNoMoreInteractions(repository);
    }


    private Team getTeam() {
        return new Team()
                .setId(ID)
                .setName(NAME)
                .setTeamType(TEAM_TYPE)
                .setUser(USER)
                .setTeamMembers(TEAM_MEMBERS)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }

    private TeamResponse getTeamResponse() {
        return new TeamResponse()
                .setTeamId(ID)
                .setName(NAME)
                .setTypeTeam(TEAM_TYPE_RESPONSE)
                .setMembers(MEMBERS_RESPONSE)
                .setUser(USER_RESPONSE)
                .setDescription(DESCRIPTION)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private TeamAddRequest getTeamAddRequest() {
        return new TeamAddRequest()
                .setTypeTeamId(TEAM_TYPE_ID)
                .setMembers(MEMBERS)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }



}