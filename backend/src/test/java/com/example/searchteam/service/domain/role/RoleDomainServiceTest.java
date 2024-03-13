package com.example.searchteam.service.domain.role;


import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.domain.role.RoleTypeEnum;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.mapper.role.RoleMapper;
import com.example.searchteam.mapper.role.RoleMerger;
import com.example.searchteam.mapper.role.RoleResponseMapper;
import com.example.searchteam.repository.role.RoleRepository;
import com.example.searchteam.service.domain.role.RoleDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final RoleType ROLE_TYPE = null;
    private static final RoleTypeEnum ROLE_TYPE_ENUM = RoleTypeEnum.TEAM;
    private static final Long ROLE_TYPE_ID=0L;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private RoleRepository repository;
    @Mock
    private RoleResponseMapper responseMapper;
    @Mock
    private RoleMapper mapper;
    @Mock
    private RoleMerger merger;

    @InjectMocks
    private RoleDomainService domainService;

    @Test
    void getRoleByIdTest() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(getRole()));
        when(responseMapper.from((Role)any())).thenReturn(getRoleResponse());

        var result = domainService.getRoleById(any());

        assertEquals(ID, result.getRoleId());
        assertEquals(NAME, result.getName());
        assertEquals(ROLE_TYPE_ENUM, result.getRoleType());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());

        verify(repository).findById(any());
        verify(responseMapper).from((Role)any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(responseMapper);
    }


    @Test
    void getAllRolesTest() {
        when(repository.findAll()).thenReturn(List.of(getRole(),getRole(),getRole()));
        var result = domainService.getAllRoles();
        for(RoleResponse e:result) {
            assertEquals(ID, e.getRoleId());
            assertEquals(NAME, e.getName());
            assertEquals(ROLE_TYPE_ENUM, e.getRoleType());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreated());
            assertEquals(MODIFIED, e.getModified());
        }
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addRoleTest() {

        when(repository.save(any())).thenReturn(getRole());
        //when(repository.addRole(any())).thenReturn(ID);

        var result = domainService.addRole(getRoleAddRequest());

        assertEquals(ID, result);

        //verify(domainService).getRoleById(any());
        verify(repository).save(any());
        verifyNoMoreInteractions(repository);

    }


    @Test
    void editRoleTest() {
        when(repository.save(any())).thenReturn(getRole());
        when(repository.getReferenceById(any())).thenReturn(getRole());

        var result = domainService.editRole(getRoleAddRequest());

        assertEquals(ID, result);

        verify(repository).save(any());
        verify(repository).getReferenceById(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteRoleTest() {
        domainService.deleteRoleById(ID);
        verify(repository).deleteById(any());
        verifyNoMoreInteractions(repository);
    }


    private RoleRequest getRoleRequest() {
        return new RoleRequest()
                .setRoleId(ID);
    }

    private Role getRole() {
        return new Role()
                .setId(ID)
                .setName(NAME)
                .setRoleType(ROLE_TYPE)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }

    private RoleResponse getRoleResponse() {
        return new RoleResponse()
                .setRoleId(ID)
                .setName(NAME)
                .setRoleType(ROLE_TYPE_ENUM)
                .setDescription(DESCRIPTION)
                .setCreated(CREATED)
                .setModified(MODIFIED);

    }

    private RoleAddRequest getRoleAddRequest() {
        return new RoleAddRequest()
                .setRoleType(ROLE_TYPE_ENUM)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }



}