package com.example.searchteam.dto.request.team;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TeamFiltrationRequest {

    private List<String> teamTypes = Collections.emptyList();
    private Boolean isCompleted;
    private String name;
    private LocalDateTime start;
    private LocalDateTime finish;
    private List<String> users = Collections.emptyList();
    private List<String> members = Collections.emptyList();

}