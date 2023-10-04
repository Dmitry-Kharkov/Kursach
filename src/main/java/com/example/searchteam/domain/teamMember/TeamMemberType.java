package com.example.searchteam.domain.teamMember;

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

@Entity(name = "TEAM_MEMBER_TYPE")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "TEAM_MEMBER_TYPE_ID"))
public class TeamMemberType extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamMemberType(Long teamMemberTypeId) {
        this.id = teamMemberTypeId;
    }


    @Override
    public TeamMemberType setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public TeamMemberType setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TeamMemberType setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TeamMemberType setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public TeamMemberType setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
