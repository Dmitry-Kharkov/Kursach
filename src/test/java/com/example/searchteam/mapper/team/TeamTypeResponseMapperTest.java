package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import com.example.searchteam.mapper.team_member.TeamMemberResponseMapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import liquibase.datatype.core.DateTimeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TeamTypeResponseMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<TeamMemberAddRequest> MEMBERS = null;
    private static final Long USER_ID = 0L;
    private static final User USER = new User(ID);
    private static final Long TEAM_TYPE_ID = 0L;
    private static final TeamType TEAM_TYPE = new TeamType(ID);
    private static final List<TeamMember> TEAM_MEMBERS = List.of();
    private static final List<Applicant> APPLICANTS = List.of();
    private static final LocalDateTime CREATED = LocalDateTime.now();

    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @InjectMocks
    private TeamTypeResponseMapper mapper;

    @Test
    void fromTest() {

        var source = spy(getTeamType());

        var result = mapper.from(source);

        assertEquals(ID, result.getTypeTeamId());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());


        verify(source).getId();
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getCreatedDateTime();
        verify(source).getModifiedDateTime();
        verifyNoMoreInteractions(source);

    }

    private TeamType getTeamType() {
        return new TeamType()
                .setName(NAME)
                .setApplicants(APPLICANTS)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }
}



