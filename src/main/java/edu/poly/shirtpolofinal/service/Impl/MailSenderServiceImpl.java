package edu.poly.shirtpolofinal.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl {
    @Autowired
    private JavaMailSender javaMailSender;
     public void sendMail(String toEmail,String subject,String body){
         SimpleMailMessage message=new SimpleMailMessage();
         message.setFrom("hoangtanh21102002@gmail.com");
         message.setTo(toEmail);
         message.setText(body);
         message.setSubject(subject);
         javaMailSender.send(message);
         System.out.println("Mail sent successfully");
     }
}
