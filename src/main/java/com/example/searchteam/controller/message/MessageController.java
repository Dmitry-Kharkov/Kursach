package com.example.searchteam.controller.message;

import com.example.searchteam.dto.request.message.MessageRequest;
import com.example.searchteam.dto.response.message.MessageResponse;
import com.example.searchteam.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class MessageController {

    public static final String MESSAGE_GET_BY_ID = "/api/v1/message/get-by-id";

    private final MessageService service;

    @PostMapping(
            value = MESSAGE_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public MessageResponse getMessageById(@RequestBody MessageRequest request){
        return service.getMessageById(request);
    }



}