package com.example.searchteam.domain.message;

import com.example.searchteam.domain.BaseEntity;
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

@Entity(name = "MESSAGE")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AttributeOverride(name = "id", column = @Column(name = "MESSAGE_ID"))

/**
 * Сущность сообщения
 * @deprecated Создание сообщения для общения внутри сайта
 */
public class Message extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Переменная текста сообщения
     * @deprecated текст
     */
    @Column(name="TEXT")
    private String text;

    /**
     * Конструктор сообщения
     * @param messageId - id сообщения
     */
    public Message(Long messageId) {
        this.id = messageId;
    }

    /**
     * Переменная id отправителя
     * @deprecated id пользователя, написавшего сообщение
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    /**
     * Переменная id получателя
     * @deprecated id пользователя, получившего сообщение
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    /**
     * Метод изменения id
     * @param id - идентификатор
     */
    @Override
    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Метод изменения времени создания
     * @param createdDateTime - время создания
     */
    @Override
    public Message setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Метод изменения времени изменения
     * @param modifiedDateTime - время изменения
     */
    @Override
    public Message setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
