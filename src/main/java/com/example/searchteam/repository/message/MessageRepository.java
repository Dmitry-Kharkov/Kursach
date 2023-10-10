package com.example.searchteam.repository.message;

import com.example.searchteam.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getMessageById(Long id);
    List<Message> getMessageByFromUserId(Long fromUserId);
    List<Message> getMessageByToUserId(Long toUserId);
    void deleteMessageById(Long id);
}
