package com.example.searchteam.dto.request.applicant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ApplicantRequest {
    private Long applicantId;
}

