package com.example.searchteam.mapper.teamMember;


import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.mapper.applicant.ApplicantResponseMapper;
import com.example.searchteam.mapper.team_member.TeamMemberResponseMapper;
import com.example.searchteam.mapper.team_member.TeamMemberTypeResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamMemberResponseMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Long TEAM_ID=0L;
    private static final Long TYPE_ID=0L;
    private static final Long APPLICANT_ID=0L;
    private static final TeamMemberType TEAM_MEMBER_TYPE=new TeamMemberType().setId(ID);

    private static final Applicant APPLICANT=new Applicant(ID).setName(NAME).setDescription(DESCRIPTION);

    private static final LocalDateTime CREATED=now();
    private static final LocalDateTime MODIFIED=now().plusMinutes(2);

    @Mock
    private ApplicantResponseMapper applicantResponseMapper;

    @Mock
    private TeamMemberTypeResponseMapper teamMemberTypeResponseMapper;
    @InjectMocks
    private TeamMemberResponseMapper mapper;
    @Test
    void from(){

        var source = spy(getTeamMember());

        var result = mapper.from(source);

        assertEquals(ID,result.getTeamMemberId());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED,result.getModified());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(TEAM_MEMBER_TYPE.getId(),result.getTeamMemberId());

        verify(source).getName();
        verify(source).getDescription();
        verify(source).getId();
        verify(source).getTeamMemberType();
        verify(source,times(2)).getApplicant();
        verify(source).getCreatedDateTime();
        verify(source).getModifiedDateTime();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getTeamMember());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertEquals(ID,result.getTeamMemberId());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED,result.getModified());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(TEAM_MEMBER_TYPE.getId(),result.getTeamMemberId());

        verify(source,times(3)).getName();
        verify(source,times(3)).getDescription();
        verify(source,times(3)).getId();
        verify(source,times(3)).getTeamMemberType();
        verify(source,times(6)).getApplicant();
        verify(source,times(3)).getCreatedDateTime();
        verify(source,times(3)).getModifiedDateTime();
        verifyNoMoreInteractions(source);


    }

    private TeamMember getTeamMember() {
        return new TeamMember()
                .setId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setApplicant(APPLICANT)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }


}
