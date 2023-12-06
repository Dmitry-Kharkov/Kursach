package com.example.searchteam.service.domain.teamMember;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.repository.role.RoleTypeRepository;
import com.example.searchteam.repository.team_member.TeamMemberTypeRepository;
import com.example.searchteam.service.domain.role.RoleTypeDomainService;
import com.example.searchteam.service.domain.team_member.TeamMemberTypeDomainService;
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
class RoleTypeDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<TeamMember> TEAM_MEMBERS = null;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamMemberTypeRepository repository;

    @InjectMocks
    private TeamMemberTypeDomainService domainService;

    @Test
    void getTeamMemberTypeByNameTest() {
        when(repository.getTeamMemberTypeByName(any())).thenReturn(List.of(getTeamMemberType(),getTeamMemberType(),getTeamMemberType()));

        var result = domainService.getTeamMemberTypeByName(any());
        for(TeamMemberType e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamMemberTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteTeamMemberTypeTest() {
        domainService.deleteTeamMemberTypeByName(NAME);
        verify(repository).deleteTeamMemberTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    private TeamMemberType getTeamMemberType() {
        return new TeamMemberType()
                .setId(ID)
                .setName(NAME)
                .setTeamMembers(TEAM_MEMBERS)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }



}