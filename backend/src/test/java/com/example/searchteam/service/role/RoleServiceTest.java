package com.example.searchteam.service.role;

import com.example.searchteam.domain.role.RoleTypeEnum;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.domain.role.RoleDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final RoleTypeEnum ROLE_TYPE = RoleTypeEnum.TEAM;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private RoleDomainService domainService;

    @InjectMocks
    private RoleService service;

    @Test
    void getRoleByIdTest() {
        when(domainService.getRoleById(any())).thenReturn(getRoleResponse());

        var result = service.getRoleById(getRoleRequest());

        assertEquals(ID, result.getRoleId());
        assertEquals(NAME, result.getName());
        assertEquals(ROLE_TYPE, result.getRoleType());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getRoleById(any());
        verifyNoMoreInteractions(domainService);
    }


    @Test
    void getAllRolesTest() {
        when(domainService.getAllRoles()).thenReturn(List.of(getRoleResponse(),getRoleResponse(),getRoleResponse()));
        var result = service.getAllRoles();
        for(RoleResponse e:result) {
                assertEquals(ID, e.getRoleId());
                assertEquals(NAME, e.getName());
                assertEquals(ROLE_TYPE, e.getRoleType());
                assertEquals(DESCRIPTION, e.getDescription());
                assertEquals(CREATED, e.getCreated());
                assertEquals(MODIFIED, e.getModified());
        }
        verify(domainService).getAllRoles();
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void addRoleTest() {

        when(domainService.getRoleById(any())).thenReturn(getRoleResponse());
        when(domainService.addRole(any())).thenReturn(ID);

        var result = service.addRole(getRoleAddRequest());

        assertEquals(ID, result.getRoleId());
        assertEquals(NAME, result.getName());
        assertEquals(ROLE_TYPE, result.getRoleType());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).getRoleById(any());
        verify(domainService).addRole(any());
        verifyNoMoreInteractions(domainService);

    }


    @Test
    void editRoleTest() {
        when(domainService.editRole(any())).thenReturn(getRoleResponse().getRoleId());
        when(domainService.getRoleById(any())).thenReturn(getRoleResponse());

        var result = service.editRole(getRoleAddRequest());

        assertEquals(ID, result.getRoleId());
        assertEquals(NAME, result.getName());
        assertEquals(ROLE_TYPE, result.getRoleType());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(domainService).editRole(any());
        verify(domainService).getRoleById(any());
        verifyNoMoreInteractions(domainService);
    }

    @Test
    void deleteRoleTest() {
        service.deleteRole(getRoleRequest());
        verify(domainService).deleteRoleById(any());
        verifyNoMoreInteractions(domainService);
    }


    private RoleRequest getRoleRequest() {
        return new RoleRequest()
                .setRoleId(ID);
    }

    private RoleResponse getRoleResponse() {
        return new RoleResponse()
                .setRoleId(ID)
                .setName(NAME)
                .setRoleType(ROLE_TYPE)
                .setDescription(DESCRIPTION)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private RoleAddRequest getRoleAddRequest() {
        return new RoleAddRequest()
                .setRoleType(ROLE_TYPE)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }



}
