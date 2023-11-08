package com.example.searchteam.service.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.service.domain.applicant.ApplicantDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantDomainService service;

    public ApplicantResponse getApplicantById(ApplicantRequest request ){
        return service.getApplicantById(request.getApplicantId());
    }

    public List<ApplicantResponse> getAllApplicants(){
        return service.getAllApplicants();
    }

    public ApplicantResponse addApplicant(ApplicantAddRequest request) {
        Long applicantId = service.addApplicant(request);
        return service.getApplicantById(applicantId);
    }


    public ApplicantResponse editApplicant(ApplicantAddRequest request) {
        Long applicantId = service.editApplicant(request);
        return service.getApplicantById(applicantId);
    }
    public void deleteApplicant(ApplicantRequest request) {
        service.deleteApplicantById(request.getApplicantId());
    }
}
