package com.example.searchteam.repository.team;

import com.example.searchteam.domain.team.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamTypeRepository extends JpaRepository<TeamType, Long > {
    List<TeamType> getTeamTypeByName(String name);

    void deleteTeamTypeByName(String name);
}