package com.example.searchteam.dto.response.message;

import com.example.searchteam.dto.response.user.UserResponse;
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
    private UserResponse fromUser;
    private UserResponse toUser;
    private String text;
    private LocalDateTime created;
    private LocalDateTime modified;

}