package com.example.searchteam.domain.team_member;


import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.applicant.Applicant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamMemberTypeTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static List<Applicant> APPLICANTS=null;
    private final static List<TeamMember> MEMBERS=null;

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(7);
    }


    @Test
    void testNoArgsConstructor() {

        var teamMemberType = getTeamMemberType();
        var teamMemberType2 = new TeamMemberType(-ID);

        assertEquals(ID, teamMemberType.getId());
        assertEquals(NAME, teamMemberType.getName());
        assertEquals(MEMBERS, teamMemberType.getTeamMembers());
        assertEquals(DESCRIPTION, teamMemberType.getDescription());
        assertEquals(APPLICANTS, teamMemberType.getApplicants());
        assertEquals(CREATED, teamMemberType.getCreatedDateTime());
        assertEquals(MODIFIED, teamMemberType.getModifiedDateTime());


        assertEquals(-ID, teamMemberType2.getId());

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
