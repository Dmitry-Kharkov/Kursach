package com.example.searchteam.service.domain.role;

import com.example.searchteam.domain.role.RoleType;
import com.example.searchteam.dto.request.role.RoleTypeAddRequest;
import com.example.searchteam.dto.response.role.RoleTypeResponse;
import com.example.searchteam.mapper.role.RoleTypeMerger;
import com.example.searchteam.mapper.role.RoleTypeResponseMapper;
import com.example.searchteam.repository.role.RoleTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTypeDomainService {

    private final RoleTypeRepository repository;
    private final RoleTypeResponseMapper mapper;
    private final RoleTypeMerger roleTypeMerger;


    @Transactional
    public List<RoleType> getRoleTypeByName(String name) {
        return repository.getRoleTypeByName(name);
    }

    @Transactional
    public RoleTypeResponse getRoleTypeById(Long id) {
        return mapper.from(repository.findById(id).orElseThrow());
    }

    @Transactional
    public List<RoleTypeResponse> getAllRoleTypes(){return mapper.from(repository.findAll());}

    @Transactional
    public void deleteRoleTypeByName(String name) {
        repository.deleteRoleTypeByName(name);
    }
    @Transactional
    public Long editRoleType(RoleTypeAddRequest request) {
        var roleType= repository.getReferenceById(request.getId());
        return repository.save(roleTypeMerger.merge(roleType, request)).getId();
    }

}
