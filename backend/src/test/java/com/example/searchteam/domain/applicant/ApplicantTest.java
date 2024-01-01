package com.example.searchteam.domain.applicant;

import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.domain.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "description";
    private final static User USER = new User();
    private final static TeamType TEAM_TYPE = new TeamType();
    private final static TeamMemberType TEAM_MEMBER_TYPE=null;
    private final static List<TeamMember> MEMBERS = List.of(new TeamMember().setId(ID).setName(NAME));

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(10);
    }


    @Test
    void testNoArgsConstructor() {

        var applicant = getApplicant();
        var applicant2 = new Applicant(-ID);

        assertEquals(ID, applicant.getId());
        assertEquals(NAME, applicant.getName());
        assertEquals(USER, applicant.getUser());
        assertNull(applicant.getTeamMemberType());
        assertEquals(TEAM_TYPE, applicant.getTeamType());
        assertEquals(MEMBERS, applicant.getTeamMembers());
        assertEquals(DESCRIPTION, applicant.getDescription());
        assertFalse(applicant.isCompleted());
        assertEquals(CREATED, applicant.getCreatedDateTime());
        assertEquals(MODIFIED, applicant.getModifiedDateTime());

        assertEquals(-ID, applicant2.getId());

    }


    private Applicant getApplicant() {
        return new Applicant()
                .setId(ID)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMembers(MEMBERS)
                .setUser(USER)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setCompleted(false)
                .setTeamType(TEAM_TYPE)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
