package com.example.searchteam.mapper.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantResponseMapper implements Mapper<ApplicantResponse, Applicant> {
    @Override
    public ApplicantResponse from(Applicant source) {
        return new ApplicantResponse()
                .setApplicantId(source.getId())
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setTypeTeamId(source.getTeamType().getId())
                .setTeamMemberTypeId(source.getTeamMemberType().getId())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
