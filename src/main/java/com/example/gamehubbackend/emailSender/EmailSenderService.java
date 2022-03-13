package com.example.gamehubbackend.emailSender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Slf4j
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void setMailSender(String toEmail, String body, String subject){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);

//        mailSender.send(message);
        System.out.println("Mail Send...");
        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            message.setSubject(subject);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
//            MimeMessageHelper helper;
            helper.setSubject(subject);
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("chrisleebed@gmail.com");
            helper.setTo(toEmail);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.error("the email service not working");
//            Logger.getLogger(HTMLMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
