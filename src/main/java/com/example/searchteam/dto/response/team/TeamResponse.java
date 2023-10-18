package com.example.searchteam.dto.response.team;

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
    private Long userId;
    private String description;
    private Long typeTeamId;
    private LocalDateTime created;
    private LocalDateTime modified;

}


