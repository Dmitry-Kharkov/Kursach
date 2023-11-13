package com.example.searchteam.dto.response.team;

import com.example.searchteam.dto.response.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ShortTeamResponse {

    private Long teamId;
    private String name;
    private UserResponse user;

    public ShortTeamResponse(Long id,String name,UserResponse user){
        this.teamId=id;
        this.name=name;
        this.user=user;
    }

}