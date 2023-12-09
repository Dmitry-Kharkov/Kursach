package com.example.searchteam.service.domain.teamMember;

import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import com.example.searchteam.mapper.team_member.TeamMemberResponseMapper;
import com.example.searchteam.repository.team_member.TeamMemberRepository;
import com.example.searchteam.service.domain.team_member.TeamMemberDomainService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamMemberDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final TeamMemberTypeResponse TEAM_MEMBER_TYPE = null;
    private static final ApplicantResponse APPLICANT=null;
    private static final TeamMemberType TEAM_TYPE=null;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamMemberRepository repository;
    @Mock
    private TeamMemberResponseMapper responseMapper;
    @Mock
    private TeamMemberMapper mapper;


    @InjectMocks
    private TeamMemberDomainService domainService;

    @Test
    void getTeamMemberByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(getTeamMember()));
        when(responseMapper.from((TeamMember)any())).thenReturn(getTeamMemberResponse());

        var result = domainService.getTeamMemberById(any());

        assertEquals(ID, result.getTeamMemberId());
        assertEquals(NAME, result.getName());
        assertEquals(TEAM_MEMBER_TYPE,result.getTeamMemberType());
        assertEquals(APPLICANT,result.getApplicant());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(repository).findById(any());
        verify(responseMapper).from((TeamMember)any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(responseMapper);
    }


    @Test
    void getTeamMemberByNameTest() {
        when(repository.getTeamMemberByName(any())).thenReturn(List.of(getTeamMember(),getTeamMember(),getTeamMember()));

        var result = domainService.getTeamMemberByName(any());

        for(TeamMember e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamMemberType());
            assertNull(e.getTeam());
            assertEquals(APPLICANT, e.getApplicant());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamMemberByName(any());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void getTeamMemberByTeamMemberIdTest() {
        when(repository.getTeamMemberByTeamMemberTypeId(any())).thenReturn(List.of(getTeamMember(),getTeamMember(),getTeamMember()));

        var result = domainService.getTeamMemberByTeamMemberTypeId(any());

        for(TeamMember e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamMemberType());
            assertNull(e.getTeam());
            assertEquals(APPLICANT, e.getApplicant());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamMemberByTeamMemberTypeId(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getTeamMemberByTeamIdTest() {
        when(repository.getTeamMemberByTeamId(any())).thenReturn(List.of(getTeamMember(),getTeamMember(),getTeamMember()));

        var result = domainService.getTeamMemberByTeamId(any());

        for(TeamMember e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(TEAM_TYPE, e.getTeamMemberType());
            assertNull(e.getTeam());
            assertEquals(APPLICANT, e.getApplicant());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamMemberByTeamId(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteTeamMemberByNameTest(){
        domainService.deleteTeamMemberByName(NAME);
        verify(repository).deleteTeamMemberByName(any());
        verifyNoMoreInteractions(repository);
    }

    private TeamMember getTeamMember() {
        return new TeamMember()
                .setId(ID)
                .setName(NAME)
                .setTeamMemberType(TEAM_TYPE)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }

    private TeamMemberResponse getTeamMemberResponse() {
        return new TeamMemberResponse()
                .setTeamMemberId(ID)
                .setName(NAME)
                .setApplicant(APPLICANT)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setDescription(DESCRIPTION)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }


}
