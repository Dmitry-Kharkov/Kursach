package com.example.searchteam.domain.role;


import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.user.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "description";
    private final static RoleType ROLE_TYPE=null;
    private final static List<Role> ROLES=null;

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(7);
    }


    @Test
    void testNoArgsConstructor() {

        var roleType = getRoleType();
        var roleType2 = new RoleType(-ID);

        assertEquals(ID, roleType.getId());
        assertEquals(NAME, roleType.getName());
        assertNull(roleType.getRoles());
        assertEquals(DESCRIPTION, roleType.getDescription());
        assertEquals(CREATED, roleType.getCreatedDateTime());
        assertEquals(MODIFIED, roleType.getModifiedDateTime());

        assertEquals(-ID, roleType2.getId());

    }


    private RoleType getRoleType() {
        return new RoleType()
                .setId(ID)
                .setName(NAME)
                .setRoles(ROLES)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
