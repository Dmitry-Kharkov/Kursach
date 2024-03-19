package com.example.searchteam.controller.message;

import com.example.searchteam.dto.request.message.MessageRequest;
import com.example.searchteam.dto.response.message.MessageResponse;
import com.example.searchteam.service.message.MessageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor

/**
 * Контроллер для сообщения
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Управление сообщениями",
                description = "Контроллер управления сообщениями", version = "1.0.0"
        )
)
public class MessageController {

    public static final String MESSAGE_GET_BY_ID = "/api/v1/message/get-by-id";

    private final MessageService service;

    /**
     * Запрос получения сообщения по id
     * @param request - messageId
     * @return сообщение
     */
    @Operation(summary="Получение сообщения по ID")
    @PostMapping(
            value = MESSAGE_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public MessageResponse getMessageById(@RequestBody MessageRequest request){
        return service.getMessageById(request);
    }



}