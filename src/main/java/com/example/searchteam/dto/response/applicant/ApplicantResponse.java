package com.example.searchteam.dto.response.applicant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ApplicantResponse {

    private Long applicantId;
    private String name;
    private String description;
    private Long userId;
    private Long typeTeamId;
    private Long teamMemberTypeId;
    private LocalDateTime created;
    private LocalDateTime modified;

}
