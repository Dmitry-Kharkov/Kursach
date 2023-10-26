package com.example.searchteam.dto.response.team;

import com.example.searchteam.dto.response.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamResponse {

    private Long teamId;
    private String name;
    private UserResponse user;
    private String description;
    private TypeTeamResponse typeTeam;
    private LocalDateTime created;
    private LocalDateTime modified;

}


