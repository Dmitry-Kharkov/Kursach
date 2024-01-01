package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.RoleTypeEnum;
import com.example.searchteam.dto.request.role.RoleAddRequest;
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
class RoleMapperTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final RoleTypeEnum ROLE_TYPE = RoleTypeEnum.TEAM;

    @InjectMocks
    private RoleMapper mapper;
    @Test
    void fromTest(){

        var source = spy(getRoleAddRequest());

        var result = mapper.from(source);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ROLE_TYPE.ordinal(), result.getRoleType().getId());

        verify(source).getName();
        verify(source).getDescription();
        verify(source).getRoleTypeId();
        verifyNoMoreInteractions(source);

    }


    @Test
    void fromListTest(){

        var source = spy(getRoleAddRequest());

        var resultList = mapper.from(List.of(source,source,source));

        assertEquals(3, resultList.size());

        var result = resultList.get(0);

        assertNull(result.getId());
        assertNull(result.getCreatedDateTime());
        assertNull(result.getModifiedDateTime());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(ROLE_TYPE.ordinal(), result.getRoleType().getId());

        verify(source, times(3)).getName();
        verify(source,times(3)).getDescription();
        verify(source, times(3)).getRoleTypeId();
        verifyNoMoreInteractions(source);

    }



    private RoleAddRequest getRoleAddRequest() {
        return new RoleAddRequest()
                .setRoleTypeId((long) ROLE_TYPE.ordinal())
                .setName(NAME)
                .setDescription(DESCRIPTION)
                .setId(ID);
    }


}
