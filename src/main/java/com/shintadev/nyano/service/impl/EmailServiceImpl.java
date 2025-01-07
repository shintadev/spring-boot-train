package com.shintadev.nyano.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shintadev.nyano.entity.mail.Email;
import com.shintadev.nyano.service.EmailService;
import com.shintadev.nyano.util.EmailSenderUtil;

@Service
public class EmailServiceImpl implements EmailService {

  @Value("${spring.mail.username}")
  private String from;

  @Autowired
  private EmailSenderUtil emailSenderUtil;

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public String sendEmail(Email email) {
    emailSenderUtil.sendEmail(email.getTo(), email.getSubject(), email.getMessageBody());
    return "Email sent successfully";
  }

  @Override
  public String sendHtmlEmail(Email email) {
    emailSenderUtil.sendHtmlEmail(email.getTo(), email.getSubject(), email.getMessageBody());
    return "Html email sent successfully";
  }

  @Override
  public String sendEmailWithAttachment(Email email) {

    return "Email with attachment sent successfully";
  }

}
