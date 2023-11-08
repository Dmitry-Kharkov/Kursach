package com.example.searchteam.service.domain.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.mapper.applicant.ApplicantMapper;
import com.example.searchteam.mapper.applicant.ApplicantMerger;
import com.example.searchteam.mapper.applicant.ApplicantResponseMapper;
import com.example.searchteam.repository.applicant.ApplicantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantDomainService {

    private final ApplicantRepository repository;
    private final ApplicantResponseMapper responseApplicantMapper;
    private final ApplicantMapper applicantMapper;
    private final ApplicantMerger applicantMerger;

    @Transactional
    public ApplicantResponse getApplicantById(Long id) {
        return responseApplicantMapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<ApplicantResponse> getAllApplicants() {
        return responseApplicantMapper.from(repository.findAll());
    }

    @Transactional
    public List<Applicant> getApplicantByName(String name) {
        return repository.getApplicantByName(name);
    }

    @Transactional
    public List<Applicant> getApplicantByTeamTypeId(Long teamTypeId) {
        return repository.getApplicantByTeamTypeId(teamTypeId);
    }

    @Transactional
    public List<Applicant> getApplicantByTeamMemberTypeId(Long teamMemberTypeId) {
        return repository.getApplicantByTeamMemberTypeId(teamMemberTypeId);
    }

    @Transactional
    public List<Applicant> getApplicantByUserId(Long userId) {
        return repository.getApplicantByUserId(userId);
    }

    @Transactional
    public void deleteApplicantByName(String name) {
        repository.deleteApplicantByName(name);
    }

    @Transactional
    public Long addApplicant(ApplicantAddRequest request) {
        return repository.save(applicantMapper.from(request)).getId();
    }

    @Transactional
    public Long editApplicant(ApplicantAddRequest request) {
        var applicant = repository.getReferenceById(request.getId());
        return repository.save(applicantMerger.merge(applicant, request)).getId();
    }
}
