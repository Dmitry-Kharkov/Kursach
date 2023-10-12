package com.example.searchteam.repository.team;

import com.example.searchteam.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long > {
    List<Team> getTeamByName(String name);
    List<Team> getTeamByUserId(Long userId);
    List<Team> getTeamByTeamTypeId(Long teamTypeId);

    void deleteTeamByName(String name);
}
