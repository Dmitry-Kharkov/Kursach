package com.example.searchteam.dto.request.applicant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ApplicantAddRequest {

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Long teamTypeId;
    private Long teamMemberTypeId;

}
