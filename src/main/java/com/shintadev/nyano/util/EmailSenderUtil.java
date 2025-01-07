package com.shintadev.nyano.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSenderUtil {

  @Value("${spring.mail.username}")
  private String from;

  @Autowired
  JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String content) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(to);
    message.setSubject(subject);
    message.setText(content);
    message.setFrom(from);

    try {
      javaMailSender.send(message);
      System.out.println("Email sent successfully");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("Failed to send email");
    }
  }

  public void sendHtmlEmail(String to, String subject, String content) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);
      helper.setFrom(from);

      javaMailSender.send(message);
      System.out.println("Html email sent successfully");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("Failed to send email");
    }
  }
}
