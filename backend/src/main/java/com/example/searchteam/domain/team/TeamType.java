package com.example.searchteam.domain.team;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.applicant.Applicant;
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

@Entity(name = "TYPE_TEAM")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "TYPE_TEAM_ID"))

/**
 * Сущность типа команды
 * @deprecated Тип команды
 */
public class TeamType extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор типа команд
     * @param teamTypeId - id типа команд
     */
    public TeamType(Long teamTypeId) {
        this.id = teamTypeId;
    }

    /**
     * Переменная заявителей
     * @deprecated список заявителей с данным типом команд
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamType")
    private List<Applicant> applicants = Collections.emptyList();

    /**
     * Переменная команд
     * @deprecated список команд данного типа команд
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamType")
    private List<Team> teams = Collections.emptyList();

    /**
     * Метод изменения id
     * @param id - идентификатор
     */
    @Override
    public TeamType setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     */
    @Override
    public TeamType setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     */
    @Override
    public TeamType setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     */
    @Override
    public TeamType setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     */
    @Override
    public TeamType setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
