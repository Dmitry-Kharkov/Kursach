package com.example.searchteam.mapper.user;

import com.example.searchteam.dto.request.user.UserAddRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "PASSWORD";

    @InjectMocks
    private UserMapper mapper;
    @Test
    void fromTest(){

        var source = spy(getUserAddRequest());

        var result = mapper.from(source);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(PASSWORD, result.getPassword());


        verify(source).getName();
        verify(source).getLogin();
        verify(source).getPassword();
        verify(source).getEmail();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getUserAddRequest());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(PASSWORD, result.getPassword());


        verify(source,times(3)).getName();
        verify(source,times(3)).getLogin();
        verify(source,times(3)).getPassword();
        verify(source,times(3)).getEmail();
        verifyNoMoreInteractions(source);

    }

    private UserAddRequest getUserAddRequest() {
        return new UserAddRequest()
                .setName(NAME)
                .setLogin(LOGIN)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setId(ID);
    }


}
