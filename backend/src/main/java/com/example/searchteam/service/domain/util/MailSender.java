package com.example.searchteam.service.domain.util;

import com.example.searchteam.config.MailFactory;
import com.example.searchteam.dto.request.util.EmailSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSender {

    private final MailFactory mailFactory;



    public void sendEmail(EmailSendRequest request){

    }


}
