package com.example.searchteam.dto.response.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class MessageResponse {

    private Long messageId;
    private Long fromUserId;
    private Long toUserId;
    private String text;
    private LocalDateTime created;
    private LocalDateTime modified;

}