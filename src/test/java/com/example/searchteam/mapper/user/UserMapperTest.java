package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.role.RoleTypeEnum;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.mapper.role.RoleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "LOGIN";
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
        assertEquals(PASSWORD, result.getPassword());


        verify(source).getName();
        verify(source).getLogin();
        verify(source).getPassword();
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
        assertEquals(PASSWORD, result.getPassword());


        verify(source,times(3)).getName();
        verify(source,times(3)).getLogin();
        verify(source,times(3)).getPassword();
        verifyNoMoreInteractions(source);

    }

    private UserAddRequest getUserAddRequest() {
        return new UserAddRequest()
                .setName(NAME)
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .setId(ID);
    }


}
