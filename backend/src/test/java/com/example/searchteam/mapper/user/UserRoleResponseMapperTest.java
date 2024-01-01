package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.mapper.role.RoleResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleResponseMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "LOGIN";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<UserRole> USER_ROLES=null;
    private static final Role ROLE=new Role().setId(ID).setUserRoles(USER_ROLES);
    private static final User USER=new User().setId(ID);
    private static final RoleType ROLE_TYPE=null;

    private static final LocalDateTime CREATED=LocalDateTime.now();
    private static final LocalDateTime MODIFIED=LocalDateTime.now().plusMinutes(2);


    @Mock
    private RoleResponseMapper roleMapper;
    @InjectMocks
    private UserRoleResponseMapper mapper;
    @Test
    void fromTest(){

        var source = spy(getUserRole());

        var result = mapper.from(source);

        assertEquals(ID,result.getUserId());
        assertNull(result.getRole());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());


        verify(source).getUser();
        verify(source).getRole();
        verify(source).getId();
        verify(source).getCreatedDateTime();
        verify(source).getModifiedDateTime();
        verify(roleMapper).from(getRole());
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getUserRole());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertEquals(ID,result.getUserId());
        assertNull(result.getRole());
        assertEquals(CREATED, result.getCreated());
        assertEquals(MODIFIED, result.getModified());


        verify(source,times(3)).getUser();
        verify(source,times(3)).getRole();
        verify(source,times(3)).getId();
        verify(source,times(3)).getCreatedDateTime();
        verify(source,times(3)).getModifiedDateTime();
        verify(roleMapper,times(3)).from(getRole());
        verifyNoMoreInteractions(source);

    }

    private UserRole getUserRole() {
        return new UserRole()
                .setRole(ROLE)
                .setUser(USER)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED)
                .setId(ID);
    }

    private Role getRole() {
        return new Role()
                .setUserRoles(USER_ROLES)
                .setRoleType(ROLE_TYPE)
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED)
                .setId(ID);
    }


}
