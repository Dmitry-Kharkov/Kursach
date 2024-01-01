package com.example.searchteam.domain.team;

import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTypeTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "description";
    private final static List<Applicant> APPLICANTS = null;


    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(7);
    }


    @Test
    void testNoArgsConstructor() {

        var teamType = getTeamType();
        var teamType2 = new TeamType(-ID);

        assertEquals(ID, teamType.getId());
        assertEquals(NAME, teamType.getName());
        assertNull(teamType.getApplicants());
        assertNotNull(teamType.getTeams());
        assertEquals(DESCRIPTION, teamType.getDescription());
        assertEquals(CREATED, teamType.getCreatedDateTime());
        assertEquals(MODIFIED, teamType.getModifiedDateTime());

        assertEquals(-ID, teamType2.getId());

    }


    private TeamType getTeamType() {
        return new TeamType()
                .setId(ID)
                .setName(NAME)
                .setApplicants(APPLICANTS)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
