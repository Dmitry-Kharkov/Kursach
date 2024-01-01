package com.example.searchteam.domain.user;

import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.role.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static User USER = null;
    private final static Role ROLE = null;


    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(5);
    }


    @Test
    void testNoArgsConstructor() {

        var userRole = getUserRole();
        var userRole2 = new UserRole(-ID);

        assertEquals(ID, userRole.getId());
        assertEquals(USER, userRole.getUser());
        assertEquals(ROLE, userRole.getRole());
        assertEquals(CREATED, userRole.getCreatedDateTime());
        assertEquals(MODIFIED, userRole.getModifiedDateTime());

        assertNull(userRole.getUser());
        assertNull(userRole.getRole());
        assertNotNull(userRole.getId());
        assertNotNull(userRole.getCreatedDateTime());
        assertNotNull(userRole.getModifiedDateTime());


        assertEquals(-ID, userRole2.getId());

    }


    private UserRole getUserRole() {
        return new UserRole()
                .setId(ID)
                .setUser(USER)
                .setRole(ROLE)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
