package com.example.searchteam.service.domain.roleType.user;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.repository.user.UserRoleRepository;
import com.example.searchteam.service.domain.user.UserRoleDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleDomainServiceTest {

    private static final Long ID = 0L;
    private static final User USER = null;
    private static final List<UserRole> USER_ROLES = List.of(new UserRole().setId(ID));
    private static final Role ROLE = new Role().setId(ID);
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private UserRoleRepository repository;

    @InjectMocks
    private UserRoleDomainService domainService;

    @Test
    void getUserRoleByUserIdTest() {
        when(repository.getUserRoleByUserId(any())).thenReturn(getUserRoles());
        var result = domainService.getUserRoleByUserId(any());

        for (UserRole e : result) {
            assertEquals(ID, e.getId());
            assertEquals(USER, e.getUser());
            assertEquals(ROLE, e.getRole());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getUserRoleByUserId(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getUserRoleByRoleIdTest() {
        when(repository.getUserRoleByRoleId(any())).thenReturn(getUserRoles());
        var result = domainService.getUserRoleByRoleId(any());

        for (UserRole e : result) {
            assertEquals(ID, e.getId());
            assertEquals(USER, e.getUser());
            assertEquals(ROLE, e.getRole());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getUserRoleByRoleId(any());
        verifyNoMoreInteractions(repository);
    }

    List<UserRole> getUserRoles(){
        return List.of(new UserRole()
                .setId(ID)
                .setRole(ROLE)
                .setUser(USER)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED));
    }


}