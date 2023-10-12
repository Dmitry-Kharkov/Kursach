package com.example.searchteam.repository.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> getApplicantByName(String name);
    List<Applicant> getApplicantByTeamTypeId(Long teamTypeId);
    List<Applicant> getApplicantByTeamMemberTypeId(Long teamMemberTypeId);
    List<Applicant> getApplicantByUserId(Long userId);
    void deleteApplicantByName(String name);
}
