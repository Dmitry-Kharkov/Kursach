package com.example.searchteam.domain.role;

import com.example.searchteam.domain.EntityWithName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity(name = "ROLE")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID"))
public class Role extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    public Role(Long roleId) {
        this.id = roleId;
    }


    @Override
    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Role setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Role setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Role setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Role setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
