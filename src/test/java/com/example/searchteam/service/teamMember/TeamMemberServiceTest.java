package com.example.searchteam.service.teamMember;

import com.example.searchteam.dto.request.team_member.TeamMemberRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.service.domain.team_member.TeamMemberDomainService;
import com.example.searchteam.service.team_member.TeamMemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamMemberServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final ApplicantResponse APPLICANT = null;
    private static final TeamMemberTypeResponse TEAM_MEMBER_TYPE = null;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamMemberDomainService domainService;

    @InjectMocks
    private TeamMemberService service;

    @Test
    void getTeamMemberByIdTest() {
        when(domainService.getTeamMemberById(any())).thenReturn(getTeamMemberResponse());

        var result = service.getTeamMemberById(getTeamMemberRequest());

        assertEquals(ID, result.getTeamMemberId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(TEAM_MEMBER_TYPE, result.getTeamMemberType());
        assertEquals(APPLICANT,result.getApplicant());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getTeamMemberById(any());
        verifyNoMoreInteractions(domainService);
    }

    private TeamMemberRequest getTeamMemberRequest() {
        return new TeamMemberRequest()
                .setTeamMemberId(ID);
    }

    private TeamMemberResponse getTeamMemberResponse() {
        return new TeamMemberResponse()
                .setTeamMemberId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setApplicant(APPLICANT)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

}
