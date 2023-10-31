package com.example.searchteam.mapper.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMemberType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.mapper.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicantMerger implements Merger<Applicant, ApplicantAddRequest> {
    @Override
    public Applicant merge(Applicant target, ApplicantAddRequest source) {
        return target
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setUser( new User(source.getUserId()))
                .setTeamType( new TeamType(source.getTeamTypeId()))
                .setTeamMemberType( new TeamMemberType(source.getTeamMemberTypeId()))
                .setModifiedDateTime(LocalDateTime.now());
    }
}
