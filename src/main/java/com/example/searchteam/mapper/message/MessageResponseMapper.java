package com.example.searchteam.mapper.message;

import com.example.searchteam.domain.message.Message;
import com.example.searchteam.dto.response.message.MessageResponse;
import com.example.searchteam.mapper.Mapper;
import com.example.searchteam.mapper.user.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageResponseMapper implements Mapper<MessageResponse, Message> {
    private final UserResponseMapper userResponseMapper;
    @Override
    public MessageResponse from(Message source) {
        return new MessageResponse()
                .setMessageId(source.getId())
                .setFromUser(userResponseMapper.from(source.getFromUser()))
                .setToUser(userResponseMapper.from(source.getToUser()))
                .setText(source.getText())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
