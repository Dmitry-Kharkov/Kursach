package com.example.searchteam.service.message;

import com.example.searchteam.dto.request.message.MessageRequest;
import com.example.searchteam.dto.request.role.RoleRequest;
import com.example.searchteam.dto.response.message.MessageResponse;
import com.example.searchteam.dto.response.role.RoleResponse;
import com.example.searchteam.service.domain.message.MessageDomainService;
import com.example.searchteam.service.domain.role.RoleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

/**
 * Сервис сообшения
 * @deprecated реализует методы обработки информации о сообщении
 */
public class MessageService {

    /**
     * Domain Service сообщения
     * @deprecated реализует методы обработки информации о сообщении
     */
    private final MessageDomainService service;

    /**
     * получение сообщения по id
     * @param request - id
     */
    public MessageResponse getMessageById(MessageRequest request ){
        return service.getMessageById(request.getMessageId());
    }

}