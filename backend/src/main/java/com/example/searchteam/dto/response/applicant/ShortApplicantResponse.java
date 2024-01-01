package com.example.searchteam.dto.response.applicant;

import com.example.searchteam.dto.response.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ShortApplicantResponse {

    private Long applicantId;
    private String name;
    private UserResponse user;

    public ShortApplicantResponse(Long id,String name,UserResponse user){
        this.applicantId=id;
        this.name=name;
        this.user=user;
    }

}
