package com.example.searchteam.domain.role;

import com.example.searchteam.domain.EntityWithName;
import com.example.searchteam.domain.user.UserRole;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity(name = "ROLE")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "ROLE_ID"))

/**
 * Сущность роли
 * Командные и системные роли пользователя
 */
public class Role extends EntityWithName {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор роли
     * @param roleId - id роли
     */
    public Role(Long roleId) {
        this.id = roleId;
    }


    /**
     * Переменная id типа роли
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_TYPE_ID")
    private RoleType roleType;

    /**
     * Переменная пользовательских ролей
     * Cписок ролей пользователя
     */
   @OneToMany(cascade = CascadeType.DETACH, mappedBy = "role")
    private List<UserRole> userRoles = Collections.emptyList();

    /**
     * Метод изменения id
     * @param id - идентификатор
     * @return роль
     */
    @Override
    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения имени
     * @param name - имя
     * @return роль
     */
    @Override
    public Role setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Метод изменения описания
     * @param description - описание
     * @return роль
     */
    @Override
    public Role setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     * @return роль
     */
    @Override
    public Role setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     * @return роль
     */
    @Override
    public Role setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
