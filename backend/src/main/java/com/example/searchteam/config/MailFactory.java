package com.example.searchteam.config;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@RequiredArgsConstructor
public class MailFactory {

    //cyvyiwyzozjfrtcg


    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();


        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);
        mailSender.setProtocol("smtp");
        mailSender.setUsername("vyushin1403");
        mailSender.setPassword("cyvyiwyzozjfrtcg");

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}
