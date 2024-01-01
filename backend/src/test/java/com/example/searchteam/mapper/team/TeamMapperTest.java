//package com.example.searchteam.mapper.team;
//
//import com.example.searchteam.domain.team_member.TeamMember;
//import com.example.searchteam.dto.request.team.TeamAddRequest;
//import com.example.searchteam.dto.request.team_member.TeamMemberAddRequest;
//import com.example.searchteam.mapper.team_member.TeamMemberMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//
//@ExtendWith(MockitoExtension.class)
//class TeamMapperTest {
//
//    private static final Long ID = 0L;
//    private static final Long USER_ID = 0L;
//    private static final Long TYPE_TEAM_ID = 0L;
//    private static final String NAME = "NAME";
//    private static final String DESCRIPTION = "DESCRIPTION";
//    private static final List<TeamMemberAddRequest> MEMBERS=List.of(new TeamMemberAddRequest().setTeamMemberId(ID));
//
//    @Mock
//    private TeamMemberMapper memberMapper;
//    @InjectMocks
//    private TeamMapper mapper;
//    @Test
//    void fromTest(){
//
//        var source = spy(getTeamAddRequest());
//
//        var result = mapper.from(source);
//        var resultMember=memberMapper.from(source.getMembers());
//
//        assertNull(result.getId());
//        assertNull(result.getCreatedDateTime());
//        assertNull(result.getModifiedDateTime());
//        assertEquals(resultMember,result.getTeamMembers());
//        assertEquals(NAME, result.getName());
//        assertEquals(DESCRIPTION, result.getDescription());
//        assertEquals(TYPE_TEAM_ID, result.getTeamType().getId());
//        assertEquals(USER_ID, result.getUser().getId());
//
//        verify(memberMapper,times(2)).from(source.getMembers());
//        verifyNoMoreInteractions(source);
//        verifyNoMoreInteractions(memberMapper);
//    }
//
//
//    @Test
//    void fromListTest(){
//
//        var source = spy(getTeamAddRequest());
//
//        var resultList = mapper.from(List.of(source,source,source));
//
//        assertEquals(3, resultList.size());
//
//        var result = resultList.get(0);
//
//        assertNull(result.getId());
//        assertNull(result.getCreatedDateTime());
//        assertNull(result.getModifiedDateTime());
//        assertEquals(MEMBERS,result.getTeamMembers());
//        assertEquals(NAME, result.getName());
//        assertEquals(DESCRIPTION, result.getDescription());
//        assertEquals(TYPE_TEAM_ID, result.getTeamType().getId());
//        assertEquals(USER_ID, result.getUser().getId());
//
//        verify(source, times(3)).getName();
//        verify(source, times(3)).getDescription();
//        verify(source, times(3)).getMembers();
//        verify(source, times(3)).getTypeTeamId();
//        verify(source, times(3)).getUserId();
//        verify(memberMapper).from((TeamMemberAddRequest) anyList());
//
//        verifyNoMoreInteractions(source);
//        verifyNoMoreInteractions(memberMapper);
//
//    }
//
//    private TeamAddRequest getTeamAddRequest() {
//        return new TeamAddRequest()
//                .setMembers(MEMBERS)
//                .setTypeTeamId(TYPE_TEAM_ID)
//                .setUserId(USER_ID)
//                .setName(NAME)
//                .setDescription(DESCRIPTION)
//                .setId(ID);
//    }
//
//
//}
