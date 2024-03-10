package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserEditPasswordRequest;
import com.example.searchteam.dto.request.user.UserEditRolesRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "admin";
    private static final String EMAIL = "email.test@gmail.com";

    private static final List<Long> ROLES=null;

    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);
    @Mock
    private UserDomainService domainService;

    @InjectMocks
    private UserService service;


    @Test
    void getUserByIdTest() {
        when(domainService.getUserById(any())).thenReturn(getUserResponse());

        var result = service.getUserById(getUserRequest());

        assertEquals(ID, result.getUserId());
        assertEquals(NAME,result.getFullName());
        assertEquals(LOGIN,result.getLogin());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getUserById(any());
        verifyNoMoreInteractions(domainService);
    }

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

    @Test
    void editPasswordUserExceptionTest(){
        var request = getUserEditPasswordRequest("123");
        assertThrows(ResponseStatusException.class,() -> service.editPasswordUser(request));
    }


    @Test
    void getAllUsersTest() {
        when(domainService.getAllUsers()).thenReturn(List.of(getUserResponse(),getUserResponse(),getUserResponse()));
        var result = service.getAllUsers();
        for(UserResponse e:result) {
            assertEquals(ID, e.getUserId());
            assertEquals(LOGIN, e.getLogin());
            assertEquals(NAME, e.getFullName());
            assertTrue(e.getRoles().isEmpty());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(domainService).getAllUsers();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void editUserTest() {
        when(domainService.editUser(any())).thenReturn(getUserResponse().getUserId());
        when(domainService.getUserById(any())).thenReturn(getUserResponse());

        var result = service.editUser(getUserAddRequest(any()));

        assertEquals(ID, result.getUserId());
        assertEquals(NAME,result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertTrue(result.getRoles().isEmpty());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editUser(any());
        verify(domainService).getUserById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void editRolesTest() {
        when(domainService.editRolesUser(any())).thenReturn(getUserResponse().getUserId());
        when(domainService.getUserById(any())).thenReturn(getUserResponse());

        var result = service.editRolesUser(getUserEditRolesRequest());

        assertEquals(ID, result.getUserId());
        assertEquals(NAME,result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertTrue(result.getRoles().isEmpty());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editRolesUser(any());
        verify(domainService).getUserById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void editPasswordUserTest(){
        when(domainService.editPasswordUser(any())).thenReturn(getUserResponse().getUserId());
        when(domainService.getUserById(any())).thenReturn(getUserResponse());

        var result = service.editPasswordUser(getUserEditPasswordRequest("QwEr123145#"));


        assertEquals(ID, result.getUserId());
        assertEquals(NAME,result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertTrue(result.getRoles().isEmpty());
        assertEquals(CREATED,result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editPasswordUser(any());
        verify(domainService).getUserById(any());
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

    private UserRequest getUserRequest() {
        return new UserRequest()
                .setUserId(ID);
    }

    private UserAddRequest getUserAddRequest(String pwd) {
        return new UserAddRequest()
                .setId(ID)
                .setName(NAME)
                .setLogin(LOGIN)
                .setEmail(EMAIL)
                .setPassword(pwd);
    }

    private UserEditPasswordRequest getUserEditPasswordRequest(String pwd){
        return new UserEditPasswordRequest()
                .setId(ID)
                .setPassword(pwd);
    }

    private UserEditRolesRequest getUserEditRolesRequest(){
        return new UserEditRolesRequest()
                .setId(ID)
                .setRoles(ROLES);
    }

}
