package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.domain.user.UserRole;
import com.example.searchteam.dto.request.user.UserAddRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserMergerTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "PASSWORD";
    private static final List<UserRole> ROLES=null;
    private static final List<Applicant> APPLICANTS=null;

    private static final LocalDateTime CREATED=null;
    private static final LocalDateTime MODIFIED=null;
    @InjectMocks
    private UserMerger merger;


    @Test
    void mergeTest(){

        var source = spy(getUserAddRequest());

        var result = merger.merge(getUser(),source);

        assertEquals(ID, result.getId());
        assertEquals(NAME, result.getFullName());
        assertEquals(LOGIN, result.getLogin());
        assertEquals(PASSWORD, result.getPassword());
        assertNull(result.getUserRoles());
        assertNull(result.getApplicants());

        verify(source).getName();
        verify(source).getLogin();
        verifyNoMoreInteractions(source);

    }

    private UserAddRequest getUserAddRequest() {
        return new UserAddRequest()
                .setName(NAME)
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .setId(ID);
    }

    private User getUser(){
        return new User()
                .setId(ID)
                .setFullName(NAME)
                .setLogin(LOGIN)
                .setUserRoles(ROLES)
                .setPassword(PASSWORD)
                .setApplicants(APPLICANTS)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);
    }


}
