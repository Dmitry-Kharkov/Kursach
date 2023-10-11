package com.example.searchteam.service.domain.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.repository.applicant.ApplicantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantDomainService {

    private final ApplicantRepository repository;


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

}
