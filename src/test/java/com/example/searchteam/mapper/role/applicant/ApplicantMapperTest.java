package com.example.searchteam.mapper.role.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.mapper.applicant.ApplicantMapper;
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
class ApplicantMapperTest {

    private static final Long ID = 0L;
    private static final Long USER_ID = 0L;
    private static final Long TEAM_TYPE_ID = 0L;
    private static final Long TEAM_MEMBER_TYPE_ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";


    @InjectMocks
    private ApplicantMapper mapper;
    @Test
    void fromTest(){

        var source = spy(getApplicantAddRequest());

        var result = mapper.from(source);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER_ID, result.getUser().getId());
        assertEquals(TEAM_TYPE_ID, result.getTeamType().getId());
        assertEquals(TEAM_MEMBER_TYPE_ID, result.getTeamMemberType().getId());

        verify(source).getName();
        verify(source).getDescription();
        verify(source).getUserId();
        verify(source).getTeamTypeId();
        verify(source).getTeamMemberTypeId();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getApplicantAddRequest());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(USER_ID, result.getUser().getId());
        assertEquals(TEAM_TYPE_ID, result.getTeamType().getId());
        assertEquals(TEAM_MEMBER_TYPE_ID, result.getTeamMemberType().getId());

        verify(source,times(3)).getName();
        verify(source,times(3)).getDescription();
        verify(source,times(3)).getUserId();
        verify(source,times(3)).getTeamTypeId();
        verify(source,times(3)).getTeamMemberTypeId();
        verifyNoMoreInteractions(source);

    }

    private ApplicantAddRequest getApplicantAddRequest() {
        return new ApplicantAddRequest()
                .setUserId(USER_ID)
                .setTeamTypeId(TEAM_TYPE_ID)
                .setTeamMemberTypeId(TEAM_MEMBER_TYPE_ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }


}
