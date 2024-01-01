package com.example.searchteam.domain.role;

import com.example.searchteam.domain.EntityWithName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity(name = "ROLE_TYPE")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "ROLE_TYPE_ID"))
public class RoleType extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    public RoleType(Long roleTypeId) {
        this.id = roleTypeId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleType")
    private List<Role> roles = Collections.emptyList();
    @Override
    public RoleType setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public RoleType setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public RoleType setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public RoleType setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public RoleType setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}