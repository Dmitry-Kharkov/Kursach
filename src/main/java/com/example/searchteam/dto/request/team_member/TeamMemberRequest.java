package com.example.searchteam.dto.request.team_member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class TeamMemberRequest {
    private Long teamMemberId;
}
