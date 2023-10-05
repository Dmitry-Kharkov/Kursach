package com.example.searchteam.domain.user;

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

@Entity(name = "USERS")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
public class User extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="FULLNAME")
    String fullName;
    @Column(name="LOGIN")
    String login;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> userRoles = Collections.emptyList();

    public User(Long userId) {
        this.id = userId;
    }


    @Override
    public User setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public User setDescription(String description) {
        this.description = description;
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
