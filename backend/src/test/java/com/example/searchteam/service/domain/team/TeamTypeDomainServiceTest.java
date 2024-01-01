package com.example.searchteam.service.domain.team;


import com.example.searchteam.domain.team.Team;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.repository.team.TeamTypeRepository;
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
class TeamTypeDomainServiceTest {

    private static final Long ID = 0L;
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final List<Team> TEAMS = null;
    private static final LocalDateTime CREATED = LocalDateTime.now();
    private static final LocalDateTime MODIFIED = LocalDateTime.now().plusMinutes(2);


    @Mock
    private TeamTypeRepository repository;

    @InjectMocks
    private TeamTypeDomainService domainService;

    @Test
    void getTeamTypeByNameTest() {
        when(repository.getTeamTypeByName(any())).thenReturn(List.of(getTeamType(),getTeamType(),getTeamType()));

        var result = domainService.getTeamTypeByName(any());
        for(TeamType e:result) {
            assertEquals(ID, e.getId());
            assertEquals(NAME, e.getName());
            assertEquals(DESCRIPTION, e.getDescription());
            assertEquals(CREATED, e.getCreatedDateTime());
            assertEquals(MODIFIED, e.getModifiedDateTime());
        }
        verify(repository).getTeamTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteTeamTypeTest() {
        domainService.deleteTeamTypeByName(NAME);
        verify(repository).deleteTeamTypeByName(any());
        verifyNoMoreInteractions(repository);
    }

    private TeamType getTeamType() {
        return new TeamType()
                .setId(ID)
                .setName(NAME)
                .setTeams(TEAMS)
                .setDescription(DESCRIPTION)
                .setCreatedDateTime(CREATED)
                .setModifiedDateTime(MODIFIED);

    }



}
