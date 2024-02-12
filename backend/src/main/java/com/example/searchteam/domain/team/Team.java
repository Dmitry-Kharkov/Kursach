package com.example.searchteam.domain.team;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.team_member.TeamMember;
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

/**
 * Сущность команды
 * @deprecated Создание заявок для поиска команды
 */
public class Team extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Переменная статуса команды
     * @deprecated Показывает статус заполнения команды
     */
    @Column(name="IS_COMPLETED")
    boolean isCompleted;

    /**
     * Конструктор команды
     * @param teamId - id команды
     */
    public Team(Long teamId) {
        this.id = teamId;
    }

    /**
     * Переменная id пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * Переменная id типа команды
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_TEAM_ID")
    private TeamType teamType;

    /**
     * Переменная участников команды
     * @deprecated список участников команды
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<TeamMember> teamMembers = Collections.emptyList();


    /**
     * Метод изменения id
     * @param id - идентификатор
     */
    @Override
    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     */
    @Override
    public Team setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     */
    @Override
    public Team setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     */
    @Override
    public Team setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     */
    @Override
    public Team setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
