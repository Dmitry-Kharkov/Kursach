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
public class Message extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name="TEXT")
    private String text;

    public Message(Long messageId) {
        this.id = messageId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    @Override
    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Message setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    @Override
    public Message setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
        return this;
    }

}
