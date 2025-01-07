package com.shintadev.nyano.entity.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Email {

  private String to;
  private String subject;
  private String messageBody;
  private String attachmentPath;

}
