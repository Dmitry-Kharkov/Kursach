package com.example.searchteam.domain.user;

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

@Entity(name = "USER")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
public class Users extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="FULLNAME")
    String fullName;
    @Column(name="LOGIN")
    String login;


    public Users(Long userId) {
        this.id = userId;
    }


    @Override
    public Users setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Users setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Users setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Users setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Users setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
