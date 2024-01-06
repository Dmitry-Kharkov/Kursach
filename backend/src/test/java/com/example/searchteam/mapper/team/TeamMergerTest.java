package com.example.searchteam.mapper.team;

import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.team.TeamAddRequest;
import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TeamMergerTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<TeamMemberAddRequest> MEMBERS = null;
    private static final Long USER_ID = 0L;
    private static final User USER = new User(ID);
    private static final Long TEAM_TYPE_ID = 0L;
    private static final TeamType TEAM_TYPE = new TeamType(ID);
    private static final List<TeamMember> TEAM_MEMBERS = List.of();

    @Mock
    private TeamMemberMapper teamMemberMapper;
    @InjectMocks
    private TeamMerger merger;
    @Test
    void fromTest(){

        var source = spy(getTeamAddRequest());

        var result = merger.merge(getTeam(),source);

        assertEquals(ID,result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(TEAM_MEMBERS, result.getTeamMembers());
        assertEquals(USER, result.getUser());
        assertEquals(TEAM_TYPE, result.getTeamType());
        assertEquals(DESCRIPTION, result.getDescription());


        verify(teamMemberMapper).from(source.getMembers());
        verify(source).getName();
        verify(source).getDescription();
        verify(source).getTypeTeamId();
        verify(source,times(2)).getMembers();
        verify(source).getUserId();
        verifyNoMoreInteractions(source);
        verifyNoMoreInteractions(teamMemberMapper);

    }




    private TeamAddRequest getTeamAddRequest() {
        return new TeamAddRequest()
                .setName(NAME)
                .setTypeTeamId(TEAM_TYPE_ID)
                .setUserId(USER_ID)
                .setMembers(MEMBERS)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }

    private Team getTeam() {
        return new Team()
                .setName(NAME)
                .setTeamType(TEAM_TYPE)
                .setUser(USER)
                .setTeamMembers(TEAM_MEMBERS)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }


}
