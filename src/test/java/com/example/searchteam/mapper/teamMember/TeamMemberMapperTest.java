package com.example.searchteam.mapper.teamMember;

import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
import com.example.searchteam.mapper.team_member.TeamMemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TeamMemberMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Long TEAM_ID=0L;
    private static final Long TYPE_ID=0L;
    private static final Long APPLICANT_ID=0L;


    @InjectMocks
    private TeamMemberMapper mapper;
    @Test
    void fromTest(){

        var source = spy(getTeamMemberAddRequest());

        var result = mapper.from(source);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertNull(result.getTeam());
        assertEquals(TYPE_ID, result.getTeamMemberType().getId());
        assertNull(result.getApplicant());

        verify(source).getName();
        verify(source).getDescription();
        verify(source).getTypeId();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getTeamMemberAddRequest());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertNull(result.getTeam());
        assertEquals(TYPE_ID, result.getTeamMemberType().getId());
        assertNull(result.getApplicant());

        verify(source,times(3)).getName();
        verify(source,times(3)).getDescription();
        verify(source,times(3)).getTypeId();
        verifyNoMoreInteractions(source);

    }

    private TeamMemberAddRequest getTeamMemberAddRequest() {
        return new TeamMemberAddRequest()
                .setTeamMemberId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamId(TEAM_ID)
                .setTypeId(TYPE_ID);
    }


}
