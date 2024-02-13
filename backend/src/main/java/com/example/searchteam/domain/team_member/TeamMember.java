package com.example.searchteam.domain.team_member;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.applicant.Applicant;
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

/**
 * Сущность участника команды
 * Описание участника команды
 */
public class TeamMember extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор участника команды
     * @param teamMemberId - id участника команды
     */
    public TeamMember(Long teamMemberId) {
        this.id = teamMemberId;
    }

    /**
     * Переменная id команды
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    /**
     * Переменная id типа участника команды
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_MEMBER_TYPE_ID")
    private TeamMemberType teamMemberType;

    /**
     * Переменная id заявителя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICANT_ID")
    private Applicant applicant;

    /**
     * Метод изменения id
     * @param id - идентификатор
     * @return участник команды
     */
    @Override
    public TeamMember setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     * @return участник команды
     */
    @Override
    public TeamMember setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     * @return участник команды
     */
    @Override
    public TeamMember setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     * @return участник команды
     */
    @Override
    public TeamMember setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     * @return участник команды
     */
    @Override
    public TeamMember setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
