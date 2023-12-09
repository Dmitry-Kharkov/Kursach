package com.example.searchteam.service.applicant;

import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantFiltrationRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.applicant.ShortApplicantResponse;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.dto.response.team.TypeTeamResponse;
import com.example.searchteam.dto.response.team_member.TeamMemberTypeResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.applicant.ApplicantDomainService;
import com.example.searchteam.service.domain.role.RoleDomainService;
import com.example.searchteam.service.role.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final UserResponse USER=new UserResponse().setUserId(ID).setFullName(NAME);
    private static final TypeTeamResponse TYPE_TEAM=new TypeTeamResponse().setTypeTeamId(ID).setName(NAME);
    private static final TeamMemberTypeResponse TEAM_MEMBER_TYPE=new TeamMemberTypeResponse();
    private static final Long TYPE_TEAM_ID=0L;
    private static final Long TEAM_MEMBER_TYPE_ID=0L;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private ApplicantDomainService domainService;

    @InjectMocks
    private ApplicantService service;

    @Test
    void getApplicantByIdTest() {
        when(domainService.getApplicantById(any())).thenReturn(getApplicantResponse());

        var result = service.getApplicantById(getApplicantRequest());

        assertEquals(ID, result.getApplicantId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(TEAM_MEMBER_TYPE, result.getTeamMemberType());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getApplicantById(any());
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void getAllApplicantsTest() {
        when(domainService.getAllApplicants()).thenReturn(List.of(getApplicantResponse(),getApplicantResponse(),getApplicantResponse()));
        var result = service.getAllApplicants();
        for(ApplicantResponse e:result) {
            assertEquals(ID, e.getApplicantId());
            assertEquals(NAME, e.getName());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(USER, e.getUser());
            assertEquals(TYPE_TEAM, e.getTypeTeam());
            assertEquals(TEAM_MEMBER_TYPE, e.getTeamMemberType());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(domainService).getAllApplicants();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void addApplicantTest() {

        when(domainService.getApplicantById(any())).thenReturn(getApplicantResponse());
        when(domainService.addApplicant(any())).thenReturn(ID);

        var result = service.addApplicant(getApplicantAddRequest());

        assertEquals(ID, result.getApplicantId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(TEAM_MEMBER_TYPE, result.getTeamMemberType());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getApplicantById(any());
        verify(domainService).addApplicant(any());
        verifyNoMoreInteractions(domainService);

    }


    @Test
    void editApplicantTest() {
        when(domainService.editApplicant(any())).thenReturn(getApplicantResponse().getApplicantId());
        when(domainService.getApplicantById(any())).thenReturn(getApplicantResponse());

        var result = service.editApplicant(getApplicantAddRequest());

        assertEquals(ID, result.getApplicantId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER, result.getUser());
        assertEquals(TYPE_TEAM, result.getTypeTeam());
        assertEquals(TEAM_MEMBER_TYPE, result.getTeamMemberType());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editApplicant(any());
        verify(domainService).getApplicantById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void deleteApplicantTest() {
        service.deleteApplicant(getApplicantRequest());
        verify(domainService).deleteApplicantById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void getSearchApplicantsTest(){
        when(domainService.getAllApplicants()).thenReturn(List.of(getApplicantResponse(),getApplicantResponse(),getApplicantResponse()));
        var result=service.getSearchApplicants(getApplicantFiltrationRequest());
        for(ShortApplicantResponse e:result) {
            assertEquals(ID, e.getApplicantId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService).getAllApplicants();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void applyFilterTest(){
        when(domainService.getAllApplicants()).thenReturn(List.of(getApplicantResponse(),getApplicantResponse(),getApplicantResponse()));
        var result=service.getSearchApplicants(getApplicantFiltrationRequestNotInfo());
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setFinish(CREATED.plusMinutes(-1)));
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setStart(MODIFIED));
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setIsCompleted(true));
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setName(NAME+"1"));
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setTeamTypes(List.of(DESCRIPTION)));
        service.getSearchApplicants(getApplicantFiltrationRequestNotInfo().setUsers(List.of(DESCRIPTION)));

        for(ShortApplicantResponse e:result) {
            assertEquals(ID, e.getApplicantId());
            assertEquals(NAME, e.getName());
            assertEquals(USER, e.getUser());
        }
        verify(domainService,times(7)).getAllApplicants();
        verifyNoMoreInteractions(domainService);
    }


    private ApplicantRequest getApplicantRequest() {
        return new ApplicantRequest()
                .setApplicantId(ID);
    }

    private ApplicantFiltrationRequest getApplicantFiltrationRequest() {
        return new ApplicantFiltrationRequest()
                //.setUsers(List.of( USER))
                .setStart(CREATED)
                .setFinish(MODIFIED)
                .setIsCompleted(false)
                .setName(NAME);
    }

    private ApplicantResponse getApplicantResponse() {
        return new ApplicantResponse()
                .setApplicantId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setUser(USER)
                .setTypeTeam(TYPE_TEAM)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private ApplicantAddRequest getApplicantAddRequest() {
        return new ApplicantAddRequest()
                .setId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamTypeId(TYPE_TEAM_ID)
                .setTeamMemberTypeId(TEAM_MEMBER_TYPE_ID)
                .setId(ID);
    }


    private ApplicantFiltrationRequest getApplicantFiltrationRequestNotInfo() {
        return new ApplicantFiltrationRequest();
    }

}
