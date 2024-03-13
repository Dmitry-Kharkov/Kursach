package com.example.searchteam.service.domain.util;

import com.example.searchteam.config.MailFactory;
import com.example.searchteam.dto.request.util.EmailSendRequest;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final MailFactory mailFactory;


    @Value("${spring.mail.from}")
    private String from;


    public void sendEmail(EmailSendRequest request) {

        try {
            var mailSender = mailFactory.getJavaMailSender();
            var msg = mailSender.createMimeMessage();
            var mimeMessage = new MimeMessageHelper(msg, true, "UTF-8");


            mimeMessage.setFrom(from);
            mimeMessage.setSubject(request.getSubject());
            mimeMessage.setText(request.getText());

            mimeMessage.setTo(request.getTo().toArray(new String[0]));
            mimeMessage.setCc(request.getCc().toArray(new String[0]));
            mimeMessage.setBcc(request.getBcc().toArray(new String[0]));


            request.getFiles().forEach(file -> {
                try {
                    mimeMessage.addAttachment(file.getL(), file.getR());
                } catch (MessagingException ignore) {
                }
            });

        } catch (Exception ex){
            System.out.println("Произошла ошибка при отправке письма");
        }

    }


}
