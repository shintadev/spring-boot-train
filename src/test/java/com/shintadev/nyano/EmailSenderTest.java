package com.shintadev.nyano;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.shintadev.nyano.util.EmailSenderUtil;

@SpringBootTest
public class EmailSenderTest {

  @Autowired
  private EmailSenderUtil emailSenderUtil;

  @Test
  void testSendEmail() {
    String to = "hung112357@gmail.com";
    String subject = "Test email";
    String content = "This is a test email";

    emailSenderUtil.sendEmail(to, subject, content);
  }

  @Test
  void testSendHtmlEmail() throws IOException {
    String to = "hung112357@gmail.com";
    String subject = "Test Html email";

    Resource resource = new ClassPathResource("/templates/email/otp-auth.html");
    String htmlContent = new String(resource.getInputStream().readAllBytes());
    emailSenderUtil.sendHtmlEmail(to, subject, htmlContent);
  }
}
