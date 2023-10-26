package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.teamMember.TeamMemberType;
import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.applicant.ApplicantAddRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.mapper.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserMerger implements Merger<User, UserAddRequest> {
    @Override
    public User merge(User target, UserAddRequest source) {
        return target
                .setFullName(source.getName())
                .setLogin(source.getLogin())
                .setPassword(source.getPassword())
                .setUserRoles(source.getUserRoles());
    }
}
