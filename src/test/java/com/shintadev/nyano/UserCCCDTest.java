package com.shintadev.nyano;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.shintadev.nyano.entity.user.CCCD;
import com.shintadev.nyano.entity.user.User;
import com.shintadev.nyano.repository.CCCDRepository;
import com.shintadev.nyano.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserCCCDTest {

  @Autowired
  private CCCDRepository cccdRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  @Transactional
  @Rollback(false)
  void oneToOneTest() {
    // Create user
    User user = new User();
    user.setName("John CCCD");
    user.setEmail("john" + System.currentTimeMillis() + "@cccd.com"); // Ensure unique email

    // Create CCCD with required fields
    CCCD cccd = new CCCD();
    cccd.setCCCDNumber("123456789012");
    user.setCccd(cccd);

    // Save user (cascade will save CCCD)
    User savedUser = userRepository.save(user);
  }
}
