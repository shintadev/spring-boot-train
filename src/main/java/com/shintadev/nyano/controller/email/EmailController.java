package com.shintadev.nyano.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadev.nyano.entity.mail.Email;
import com.shintadev.nyano.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @PostMapping("/send")
  public String sendEmail(@RequestBody Email email) {
    return emailService.sendEmail(email);
  }

  @PostMapping("/sendHtml")
  public String sendHtmlEmail(@RequestBody Email email) {
    return emailService.sendHtmlEmail(email);
  }
}
