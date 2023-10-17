package com.example.searchteam.mapper.message;

import com.example.searchteam.domain.message.Message;
import com.example.searchteam.dto.response.message.MessageResponse;
import com.example.searchteam.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageResponseMapper implements Mapper<MessageResponse, Message> {
    @Override
    public MessageResponse from(Message source) {
        return new MessageResponse()
                .setMessageId(source.getId())
                .setFromUserId(source.getFromUser().getId())
                .setToUserId(source.getToUser().getId())
                .setText(source.getText())
                .setCreated(source.getCreatedDateTime())
                .setModified(source.getModifiedDateTime());
    }
}
