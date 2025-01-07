package com.shintadev.nyano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shintadev.nyano.entity.mail.Email;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaService {

  @Autowired
  private EmailService emailService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @KafkaListener(topics = "otp-email", groupId = "otp-group_id")
  public void consumeOtpEmail(String message) {
    try {
      JsonNode jsonNode = objectMapper.readTree(message);
      String email = jsonNode.get("email").asText();
      String otp = jsonNode.get("otp").asText();
      log.info("Consumed message: " + message);

      Email emailEntity = new Email();
      emailEntity.setTo(email);
      emailEntity.setSubject("OTP for email verification using Kafka");
      emailEntity.setMessageBody("Your OTP is: " + otp);

      String result = emailService.sendEmail(emailEntity);
      log.info("Email sent: " + result);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
