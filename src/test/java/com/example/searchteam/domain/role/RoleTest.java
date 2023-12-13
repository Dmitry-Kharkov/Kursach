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
    private final static List<UserRole> ROLES=null;

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(7);
    }


    @Test
    void testNoArgsConstructor() {

        var role = getRole();
        var role2 = new Role(-ID);

        assertEquals(ID, role.getId());
        assertEquals(NAME, role.getName());
        assertNull(role.getUserRoles());
        assertEquals(ROLE_TYPE, role.getRoleType());
        assertEquals(DESCRIPTION, role.getDescription());
        assertEquals(CREATED, role.getCreatedDateTime());
        assertEquals(MODIFIED, role.getModifiedDateTime());

        assertEquals(-ID, role2.getId());

    }


    private Role getRole() {
        return new Role()
                .setId(ID)
                .setName(NAME)
                .setRoleType(ROLE_TYPE)
                .setUserRoles(ROLES)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
