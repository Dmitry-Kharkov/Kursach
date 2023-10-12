package com.example.searchteam.service.domain.message;

import com.example.searchteam.domain.message.Message;
import com.example.searchteam.repository.message.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageRepository repository;


    @Transactional
    public List<Message> getMessageByFromUserId(Long fromUserId) {
        return repository.getMessageByFromUserId(fromUserId);
    }

    @Transactional
    public List<Message> getMessageByToUserId(Long toUserId) {
        return repository.getMessageByToUserId(toUserId);
    }


}
