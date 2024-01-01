package com.example.searchteam.domain.user;

import com.example.searchteam.domain.BaseEntityTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String LOGIN = "login";
    private final static String PASSWORD = "pwd";
    private final static String FULL_NAME = "fullname";

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(11);
    }


    @Test
    void testNoArgsConstructor() {

        var user = getUser();
        var user2 = new User(-ID);

        assertEquals(ID, user.getId());
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(FULL_NAME, user.getFullName());
        assertEquals(CREATED, user.getCreatedDateTime());
        assertEquals(MODIFIED, user.getModifiedDateTime());

        assertNotNull(user.getUserRoles());
        assertNotNull(user.getApplicants());
        assertNotNull(user.getFromMessages());
        assertNotNull(user.getToMessages());
        assertNotNull(user.getTeams());


        assertEquals(-ID, user2.getId());

    }


    private User getUser() {
        return new User()
                .setId(ID)
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .setFullName(FULL_NAME)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
