package com.example.searchteam.domain.user;

import com.example.searchteam.domain.BaseEntity;
import com.example.searchteam.domain.applicant.Applicant;
import com.example.searchteam.domain.message.Message;
import com.example.searchteam.domain.team.Team;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "USERS")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="FULLNAME")
    String fullName;
    @Column(name="LOGIN")
    String login;
    @Column(name="PASSWORD")
    String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> userRoles = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Team> teams = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Message> fromMessages = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUser")
    private List<Message> toMessages = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Applicant> applicants = Collections.emptyList();

    public User(Long userId) {
        this.id = userId;
    }


    @Override
    public User setId(Long id) {
        this.id = id;
        return this;
    }


    @Override
    public User setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public User setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
