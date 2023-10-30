package com.example.searchteam.mapper.user;

import com.example.searchteam.domain.user.User;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserEditPasswordRequest;
import com.example.searchteam.mapper.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMerger implements Merger<User, UserAddRequest> {

    @Override
    public User merge(User target, UserAddRequest source) {
        return target
                .setLogin(source.getLogin())
                .setFullName(source.getName());
    }

    public User mergePassword(User target, UserEditPasswordRequest source) {
        return target
                .setPassword(source.getPassword());
    }
}
