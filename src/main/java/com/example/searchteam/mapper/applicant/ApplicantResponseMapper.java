package com.example.searchteam.mapper.applicant;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.dto.response.applicant.ApplicantResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.team.TeamTypeResponseMapper;
import com.example.searchteam.mapper.team_member.TeamMemberTypeResponseMapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantResponseMapper implements Mapper<ApplicantResponse, Applicant> {

    private final UserResponseMapper userResponseMapper;
    private final TeamTypeResponseMapper teamTypeResponseMapper;
    private final TeamMemberTypeResponseMapper teamMemberTypeResponseMapper;

    @Override
    public ApplicantResponse from(Applicant source) {
        return new ApplicantResponse()
                .setApplicantId(source.getId())
                .setName(source.getName())
                .setUser(userResponseMapper.from(source.getUser()))
                .setDescription(source.getDescription())
                .setTypeTeam(teamTypeResponseMapper.from(source.getTeamType()))
                .setTeamMemberType(teamMemberTypeResponseMapper.from(source.getTeamMemberType()))
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
