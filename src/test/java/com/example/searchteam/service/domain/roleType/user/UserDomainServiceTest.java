package com.example.searchteam.service.domain.roleType.user;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserEditPasswordRequest;
import com.example.searchteam.dto.request.user.UserEditRolesRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.mapper.user.UserMapper;
import com.example.searchteam.mapper.user.UserMerger;
import com.example.searchteam.mapper.user.UserResponseMapper;
import com.example.searchteam.repository.user.UserRepository;
import com.example.searchteam.repository.user.UserRoleRepository;
import com.example.searchteam.service.domain.user.UserDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final List<UserRole> USER_ROLES = List.of(new UserRole().setId(ID));
    private static final List<RoleResponse> ROLES=List.of(new RoleResponse().setRoleId(ID));
    private static final List<Long> ROLES_LONG=List.of(ID);
    private static final Role ROLE=new Role().setId(ID);
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private UserRepository repository;
    @Mock
    private UserResponseMapper responseMapper;
    @Mock
    private UserMerger merger;
    @Mock
    private UserMapper mapper;
    @Mock
    private UserRoleRepository userRoleRepository;


    @InjectMocks
    private UserDomainService domainService;

    @Test
    void getUserByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(getUser()));
        when(responseMapper.from((User)any())).thenReturn(getUserResponse());

        var result = domainService.getUserById(any());

        assertEquals(ID, result.getUserId());
        assertEquals(NAME, result.getFullName());
        assertEquals(ROLES, result.getRoles());
        assertEquals(LOGIN, result.getLogin());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(repository).findById(any());
        verify(responseMapper).from((User)any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(responseMapper);
    }

    @Test
    void getUserByFullNameTest() {
        when(repository.getUserByFullName(any())).thenReturn(List.of(getUser(),getUser(),getUser()));
        var result = domainService.getUserByFullName(any());

        for(User e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getFullName());
            assertEquals(ROLES.get(0).getRoleId(), e.getUserRoles().get(0).getId());
            assertEquals(LOGIN, e.getLogin());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getUserByFullName(any());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void getAllUsersTest() {
        when(repository.findAll()).thenReturn(List.of(getUser(),getUser(),getUser()));
        var result = domainService.getAllUsers();
        for(UserResponse e:result) {
            assertEquals(ID, e.getUserId());
            assertEquals(NAME, e.getFullName());
            assertEquals(ROLES, e.getRoles());
            assertEquals(LOGIN, e.getLogin());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addUserTest() {

        when(repository.save(any())).thenReturn(getUser());
        when(mapper.from((UserAddRequest)any())).thenReturn(getUser());
        //when(repository.addUser(any())).thenReturn(ID);

        var result = domainService.addUser(getUserAddRequest());

        assertEquals(ID, result);

        //verify(domainService).getUserById(any());
        verify(repository).save(any());
        verify(mapper).from((UserAddRequest)any());
        verifyNoMoreInteractions(repository);

    }


    @Test
    void editUserTest() {
        when(repository.save(any())).thenReturn(getUser());
        when(repository.getReferenceById(any())).thenReturn(getUser());
        when(merger.merge(any(),any())).thenReturn(getUser());
        var result = domainService.editUser(getUserAddRequest());

        assertEquals(ID, result);

        verify(repository).save(any());
        verify(repository).getReferenceById(any());
        verify(merger).merge(any(),any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editPasswordUserTest() {
        when(repository.save(any())).thenReturn(getUser());
        when(repository.getReferenceById(any())).thenReturn(getUser());
        var result = domainService.editPasswordUser(getUserEditPasswordRequest());

        assertEquals(ID, result);

        verify(repository).save(any());
        verify(repository).getReferenceById(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editRolesUserTest() {
        when(userRoleRepository.saveAll(any())).thenReturn(List.of(getUserRole()));
        when(repository.getReferenceById(any())).thenReturn(getUser());
        var result = domainService.editRolesUser(getUserEditRolesRequest());

        assertEquals(ID, result);

        verify(userRoleRepository).saveAll(any());
        verify(repository).getReferenceById(any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(userRoleRepository);
    }

    @Test
    void setUserRoleTest() {
        when(userRoleRepository.saveAll(any())).thenReturn(List.of(getUserRole()));
        domainService.setUserRole(ID,ROLES_LONG);
        verify(userRoleRepository).saveAll(any());
        verifyNoMoreInteractions(userRoleRepository);
    }

    @Test
    void deleteUserByFullNameTest() {
        domainService.deleteUserByFullName(NAME);
        verify(repository).deleteUserByFullName(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteUserByLoginTest() {
        domainService.deleteUserByLogin(LOGIN);
        verify(repository).deleteUserByLogin(any());
        verifyNoMoreInteractions(repository);
    }


    private User getUser() {
        return new User()
                .setId(ID)
                .setFullName(NAME)
                .setPassword(PASSWORD)
                .setLogin(LOGIN)
                .setUserRoles(USER_ROLES)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }

    private UserRole getUserRole() {
        return new UserRole()
                .setId(ID)
                .setRole(ROLE)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }

    private UserResponse getUserResponse() {
        return new UserResponse()
                .setUserId(ID)
                .setFullName(NAME)
                .setLogin(LOGIN)
                .setRoles(ROLES)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private UserEditPasswordRequest getUserEditPasswordRequest(){
        return new UserEditPasswordRequest()
                .setId(ID)
                .setPassword(PASSWORD);
    }

    private UserEditRolesRequest getUserEditRolesRequest(){
        return new UserEditRolesRequest()
                .setId(ID)
                .setRoles(ROLES_LONG);
    }

    private UserAddRequest getUserAddRequest() {
        return new UserAddRequest()
                .setId(ID)
                .setLogin(LOGIN)
                .setName(NAME)
                .setLogin(LOGIN);
    }



}