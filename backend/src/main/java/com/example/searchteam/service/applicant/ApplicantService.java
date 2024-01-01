package com.example.searchteam.service.applicant;

import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.applicant.ApplicantFiltrationRequest;
import com.example.searchteam.dto.request.applicant.ApplicantRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.dto.response.applicant.ShortApplicantResponse;
import com.example.searchteam.service.domain.applicant.ApplicantDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantDomainService service;

    public ApplicantResponse getApplicantById(ApplicantRequest request) {
        return service.getApplicantById(request.getApplicantId());
    }

    public List<ApplicantResponse> getAllApplicants() {
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

    public List<ShortApplicantResponse> getSearchApplicants(ApplicantFiltrationRequest request) {
        return service.getAllApplicants().stream()
                .filter(t -> applyFilter(t, request))
                .map(t -> new ShortApplicantResponse(t.getApplicantId(), t.getName(), t.getUser()))
                .toList();

    }


    private boolean applyFilter(ApplicantResponse source, ApplicantFiltrationRequest filter) {

        if (filter.getStart() == null && filter.getFinish() == null && filter.getName() == null
                && filter.getIsCompleted() == null && filter.getTeamTypes().isEmpty() && filter.getUsers().isEmpty()
        ) return true;


        if (filter.getStart() != null && (filter.getStart().isAfter(source.getCreated())))
            return false;

        if (filter.getFinish() != null && (filter.getFinish().isBefore(source.getCreated())))
            return false;

        if (filter.getIsCompleted() != null && !filter.getIsCompleted().equals(source.isCompleted()))
            return false;

        if (filter.getName() != null && !source.getName().toLowerCase().contains(filter.getName().toLowerCase())) {
            return false;
        }

        var userFilter = filter
                .getUsers()
                .stream()
                .noneMatch(u -> source.getUser().getFullName().toLowerCase().contains(u.toLowerCase()));


        if (!filter.getUsers().isEmpty() && userFilter) {
            return false;
        }

        var typeFilter = filter.getTeamTypes()
                .stream()
                .noneMatch(t -> source.getTypeTeam().getName().toLowerCase().contains(t.toLowerCase()));


        if (!filter.getTeamTypes().isEmpty() && typeFilter) {
            return false;
        }
        return true;
    }
}
