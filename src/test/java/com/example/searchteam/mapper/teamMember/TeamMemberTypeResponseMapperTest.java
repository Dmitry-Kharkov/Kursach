package com.example.searchteam.mapper.teamMember;


        import com.example.searchteam.domain.applicant.Applicant;
        import com.example.searchteam.domain.team_member.TeamMember;
        import com.example.searchteam.domain.team_member.TeamMemberType;
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
class TeamMemberTypeResponseMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<TeamMember> MEMBERS=null;
    private static final TeamMemberType TEAM_MEMBER_TYPE=new TeamMemberType().setId(ID);

    private static final List<Applicant> APPLICANTS=List.of(new Applicant(ID).setName(NAME).setDescription(DESCRIPTION));

    private static final LocalDateTime CREATED=now();
    private static final LocalDateTime MODIFIED=now().plusMinutes(2);

    @InjectMocks
    private TeamMemberTypeResponseMapper mapper;
    @Test
    void from(){

        var source = spy(getTeamMemberType());
        var result = mapper.from(source);
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED,result.getModified());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ID,result.getTeamMemberTypeId());

        verify(source).getName();
        verify(source).getDescription();
        verify(source).getId();
        verify(source).getCreatedDateTime();
        verify(source).getModifiedDateTime();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getTeamMemberType());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED,result.getModified());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ID,result.getTeamMemberTypeId());

        verify(source,times(3)).getName();
        verify(source,times(3)).getDescription();
        verify(source,times(3)).getId();
        verify(source,times(3)).getCreatedDateTime();
        verify(source,times(3)).getModifiedDateTime();
        verifyNoMoreInteractions(source);

    }

    private TeamMemberType getTeamMemberType() {
        return new TeamMemberType()
                .setId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMembers(MEMBERS)
                .setApplicants(APPLICANTS)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }


}
