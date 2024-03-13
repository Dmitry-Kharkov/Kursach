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
import java.util.Collections;
import java.util.List;


@Entity(name = "APPLICANT")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "APPLICANT_ID"))

/**
 * Сущность заявителя
 * Создание заявок для поиска команды
 */

public class Applicant extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Переменная статуса заявки
     * Показывает статус выполнения заявки
     */
    @Column(name="IS_COMPLETED")
    boolean isCompleted;

    /**
     * Конструктор заявителя
     * @param applicantId - id заявителя
     */
    public Applicant(Long applicantId) {
        this.id = applicantId;
    }


    /**
     * Переменная id пользователя
     * id пользователя, подавшего заявку
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    /**
     * Переменная id типа команды
     * Указывает тип команды, указанной в заявке
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_TEAM_ID")
    private TeamType teamType;

    /**
     * Переменная id типа участника команды
     * Указывает тип участника в заданной команде
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_MEMBER_TYPE_ID")
    private TeamMemberType teamMemberType;

    /**
     * Список участников команды
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
    private List<TeamMember> teamMembers= Collections.emptyList();


    /**
     * Метод изменения id
     * @param id - идентификатор
     * @return заявитель
     */
    @Override
    public Applicant setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     * @return заявитель
     */
    @Override
    public Applicant setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     * @return заявитель
     */
    @Override
    public Applicant setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     * @return заявитель
     */
    @Override
    public Applicant setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     * @return заявитель
     */
    @Override
    public Applicant setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
