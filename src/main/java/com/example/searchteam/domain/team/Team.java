package com.example.searchteam.domain.team;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.teamMember.TeamMember;
import com.example.searchteam.domain.user.User;
import jakarta.persistence.*;
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

@Entity(name = "TEAM")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "TEAM_ID"))
public class Team extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="IS_COMPLETED")
    boolean isCompleted;

    public Team(Long teamId) {
        this.id = teamId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_TEAM_ID")
    private TeamType teamType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<TeamMember> teamMembers = Collections.emptyList();


    @Override
    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Team setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Team setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Team setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Team setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
