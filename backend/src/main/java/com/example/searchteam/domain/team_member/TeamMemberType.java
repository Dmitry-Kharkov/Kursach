package com.example.searchteam.domain.team_member;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.applicant.Applicant;
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

    /**
     * Конструктор типа участника команды
     * @param teamMemberTypeId - id типа участника команды
     */
    public TeamMemberType(Long teamMemberTypeId) {
        this.id = teamMemberTypeId;
    }

    /**
     * Переменная заявителей
     * @deprecated список заявителей с данным типом участника команды
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamMemberType")
    private List<Applicant> applicants = Collections.emptyList();

    /**
     * Переменная участников команды
     * @deprecated список участников команды с данным типом
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamMemberType")
    private List<TeamMember> teamMembers = Collections.emptyList();

    /**
     * Метод изменения id
     * @param id - идентификатор
     */
    @Override
    public TeamMemberType setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     */
    @Override
    public TeamMemberType setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     */
    @Override
    public TeamMemberType setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     */
    @Override
    public TeamMemberType setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     */
    @Override
    public TeamMemberType setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
