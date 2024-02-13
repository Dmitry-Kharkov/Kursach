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

@Entity(name = "USERS")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))

/**
 * Сущность пользователя
 * Описание пользователя
 */
public class User extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Переменная имени пользователя
     */
    @Column(name = "FULLNAME")
    private String fullName;
    /**
     * Переменная логина пользователя
     */
    @Column(name = "LOGIN")
    private String login;
    /**
     * Переменная пароля пользователя
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * Переменная ролей пользователя
     * Список ролей пользователя
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> userRoles = Collections.emptyList();

    /**
     * Переменная команд пользователя
     * Список команд пользователя
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Team> teams = Collections.emptyList();

    /**
     * Переменная отправленных сообщений пользователя
     * Список сообщений, отправленных пользователем
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<Message> fromMessages = Collections.emptyList();

    /**
     * Переменная полученных сообщений пользователя
     * Список сообщений, полученных пользователем
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUser")
    private List<Message> toMessages = Collections.emptyList();

    /**
     * Переменная заявок пользователя
     * Список заявок, созданных пользователем
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Applicant> applicants = Collections.emptyList();

    /**
     * Конструктор пользователя
     * @param userId - id пользоателя
     */
    public User(Long userId) {
        this.id = userId;
    }

    /**
     * Метод изменения id
     * @param id - идентификатор
     * @return пользователь
     */
    @Override
    public User setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     * @return пользователь
     */
    @Override
    public User setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     * @return пользователь
     */
    @Override
    public User setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
