package com.example.searchteam.domain.teamMember;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.team.Team;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity(name = "TEAM_MEMBER")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "TEAM_MEMBER_ID"))
public class TeamMember extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamMember(Long teamMemberId) {
        this.id = teamMemberId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_MEMBER_TYPE_ID")
    private TeamMemberType teamMemberType;

    @Override
    public TeamMember setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public TeamMember setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TeamMember setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TeamMember setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public TeamMember setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
