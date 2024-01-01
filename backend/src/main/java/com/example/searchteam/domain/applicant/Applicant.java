package com.example.searchteam.domain.applicant;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.team.TeamType;
import com.example.searchteam.domain.team_member.TeamMember;
import com.example.searchteam.domain.team_member.TeamMemberType;
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
import java.util.List;

@Entity(name = "APPLICANT")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "APPLICANT_ID"))
public class Applicant extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="IS_COMPLETED")
    boolean isCompleted;

    public Applicant(Long applicantId) {
        this.id = applicantId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_TEAM_ID")
    private TeamType teamType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_MEMBER_TYPE_ID")
    private TeamMemberType teamMemberType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    private List<TeamMember> teamMembers;


    @Override
    public Applicant setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Applicant setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Applicant setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Applicant setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Applicant setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
