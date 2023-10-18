package com.example.searchteam.service.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.service.domain.applicant.ApplicantDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantDomainService service;

    public ApplicantResponse getApplicantById(ApplicantRequest request ){
        return service.getApplicantById(request.getApplicantId());
    }


}
