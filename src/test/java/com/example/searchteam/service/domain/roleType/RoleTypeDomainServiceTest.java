package com.example.searchteam.service.domain.roleType;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.repository.role.RoleTypeRepository;
import com.example.searchteam.service.domain.role.RoleTypeDomainService;
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
class RoleTypeDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<Role> ROLES = null;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private RoleTypeRepository repository;

    @InjectMocks
    private RoleTypeDomainService domainService;

    @Test
    void getRoleTypeByNameTest() {
        when(repository.getRoleTypeByName(any())).thenReturn(List.of(getRoleType(),getRoleType(),getRoleType()));

        var result = domainService.getRoleTypeByName(any());
        for(RoleType e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getRoleTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteRoleTypeTest() {
        domainService.deleteRoleTypeByName(NAME);
        verify(repository).deleteRoleTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    private RoleType getRoleType() {
        return new RoleType()
                .setId(ID)
                .setName(NAME)
                .setRoles(ROLES)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }



}