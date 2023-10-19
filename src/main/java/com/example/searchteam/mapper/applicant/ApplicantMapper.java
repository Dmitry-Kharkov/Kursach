package com.example.searchteam.mapper.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.teamMember.TeamMemberType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantMapper implements Mapper<Applicant, ApplicantAddRequest> {
    @Override
    public Applicant from(ApplicantAddRequest source) {
        return new Applicant()
                .setName(source.getName())
                .setDescription(source.getDescription())
                .setUser(new User(source.getUserId()))
                .setTeamType(new TeamType(source.getTeamTypeId()))
                .setTeamMemberType(new TeamMemberType(source.getTeamMemberTypeId()));
    }
}
