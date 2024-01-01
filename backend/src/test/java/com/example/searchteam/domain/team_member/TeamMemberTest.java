package com.example.searchteam.domain.team_member;



import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team_member.TeamMember;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeamMemberTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Team TEAM=null;
    private final static TeamMemberType TEAM_MEMBER_TYPE=null;
    private final static Applicant APPLICANT=null;

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(8);
    }


    @Test
    void testNoArgsConstructor() {

        var teamMember = getTeamMember();
        var teamMember2 = new TeamMember(-ID);

        assertEquals(ID, teamMember.getId());
        assertEquals(NAME, teamMember.getName());
        assertEquals(TEAM, teamMember.getTeam());
        assertEquals(DESCRIPTION, teamMember.getDescription());
        assertEquals(TEAM_MEMBER_TYPE, teamMember.getTeamMemberType());
        assertEquals(APPLICANT, teamMember.getApplicant());
        assertEquals(CREATED, teamMember.getCreatedDateTime());
        assertEquals(MODIFIED, teamMember.getModifiedDateTime());


        assertEquals(-ID, teamMember2.getId());

    }


    private TeamMember getTeamMember() {
        return new TeamMember()
                .setId(ID)
                .setTeam(TEAM)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setTeamMemberType(TEAM_MEMBER_TYPE)
                .setApplicant(APPLICANT)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
