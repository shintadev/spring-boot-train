package com.shintadev.nyano.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cccd")
@Data
public class CCCD {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "varchar(12) comment 'cccd number'", nullable = false)
  private String CCCDNumber;

  @OneToOne(mappedBy = "cccd", cascade = CascadeType.ALL)
  private User user;
}
