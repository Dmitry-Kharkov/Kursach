package com.example.searchteam.domain.team;

import com.example.searchteam.domain.BaseEntityTest;
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

class TeamTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "description";
    private final static List<TeamMember> TEAM_MEMBERS = null;
    private final static User USER = null;
    private final static TeamType TEAM_TYPE = null;


    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(9);
    }


    @Test
    void testNoArgsConstructor() {

        var team = getTeam();
        var team2 = new Team(-ID);

        assertEquals(ID, team.getId());
        assertEquals(NAME, team.getName());
        assertNull(team.getTeamMembers());
        assertNull(team.getUser());
        assertNull(team.getTeamType());
        assertFalse(team.isCompleted());
        assertEquals(DESCRIPTION, team.getDescription());
        assertEquals(CREATED, team.getCreatedDateTime());
        assertEquals(MODIFIED, team.getModifiedDateTime());

        assertEquals(-ID, team2.getId());

    }


    private Team getTeam() {
        return new Team()
                .setId(ID)
                .setName(NAME)
                .setTeamMembers(TEAM_MEMBERS)
                .setUser(USER)
                .setTeamType(TEAM_TYPE)
                .setCompleted(false)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
