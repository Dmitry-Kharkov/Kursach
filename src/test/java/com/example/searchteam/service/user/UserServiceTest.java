package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.user.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "LOGIN";

    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);
    @Mock
    private UserDomainService domainService;

    @InjectMocks
    private UserService service;


    @Test
    void addUserExceptionTest(){
        var request = getUserAddRequest("123");
        assertThrows(ResponseStatusException.class,() -> service.addUser(request));
    }

    @Test
    void addUserTest(){

        when(domainService.addUser(any())).thenReturn(ID);
        when(domainService.getUserById(any())).thenReturn(getUserResponse());

        var result = service.addUser(getUserAddRequest("QwEr123145#"));

        assertEquals(ID, result.getUserId());
        assertEquals(NAME,result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertTrue(result.getRoles().isEmpty());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED, result.getModified());


        verify(domainService).addUser(any());
        verify(domainService).getUserById(any());
        verify(domainService).setUserRole(any(),anyList());
        verifyNoMoreInteractions(domainService);

    }

    private UserResponse getUserResponse() {
        return new UserResponse()
                .setUserId(ID)
                .setLogin(LOGIN)
                .setFullName(NAME)
                .setCreated(CREATED)
                .setModified(MODIFIED)
                .setRoles(Collections.emptyList());

    }

    private UserAddRequest getUserAddRequest(String pwd) {
        return new UserAddRequest()
                .setId(ID)
                .setName(NAME)
                .setLogin(LOGIN)
                .setPassword(pwd);
    }

}
