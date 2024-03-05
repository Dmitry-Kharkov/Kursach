package com.example.searchteam.mapper.role;

import com.example.searchteam.domain.role.Role;
import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.request.role.RoleAddRequest;
import com.example.searchteam.dto.request.role.RoleTypeAddRequest;
import com.example.searchteam.mapper.Merger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleTypeMerger implements Merger<RoleType, RoleTypeAddRequest> {

    @Override
    public RoleType merge(RoleType target, RoleTypeAddRequest source) {
        return target
                .setName(source.getName())
                .setDescription(source.getDescription());
    }
}