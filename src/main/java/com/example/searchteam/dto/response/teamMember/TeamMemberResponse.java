package com.example.searchteam.dto.response.teamMember;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamMemberResponse {

    private Long teamMemberId;
    private String name;
    private Long teamMemberTypeId;
    private Long teamId;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;

}
