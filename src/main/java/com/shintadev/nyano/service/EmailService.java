package com.shintadev.nyano.service;

import com.shintadev.nyano.entity.mail.Email;

public interface EmailService {

  String sendEmail(Email email);

  String sendHtmlEmail(Email email);

  String sendEmailWithAttachment(Email email);
}
