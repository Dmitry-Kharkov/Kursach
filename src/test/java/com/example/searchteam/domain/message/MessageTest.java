package com.example.searchteam.domain.message;


import com.example.searchteam.domain.BaseEntityTest;
import com.example.searchteam.domain.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest extends BaseEntityTest {


    private final static Long ID = 1L;
    private final static String TEXT = "TEXT";
    private final static User USER = null;

    private final static LocalDateTime CREATED = LocalDateTime.now();
    private final static LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);

    @BeforeAll
    void initTests() throws ClassNotFoundException {
        super.checkNumFields(6);
    }


    @Test
    void testNoArgsConstructor() {

        var message = getMessage();
        var message2 = new Message(-ID);

        assertEquals(ID, message.getId());
        assertEquals(TEXT, message.getText());
        assertEquals(USER, message.getFromUser());
        assertEquals(USER, message.getToUser());
        assertEquals(CREATED, message.getCreatedDateTime());
        assertEquals(MODIFIED, message.getModifiedDateTime());

        assertEquals(-ID, message2.getId());

    }


    private Message getMessage() {
        return new Message()
                .setId(ID)
                .setText(TEXT)
                .setFromUser(USER)
                .setToUser(USER)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

}
