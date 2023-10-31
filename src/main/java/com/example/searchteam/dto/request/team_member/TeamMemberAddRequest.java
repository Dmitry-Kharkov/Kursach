package com.example.searchteam.dto.request.team_member;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TeamMemberAddRequest {

    private Long teamMemberId;
    private String name;
    private String description;
    private Long typeId;
    private Long teamId;

}
