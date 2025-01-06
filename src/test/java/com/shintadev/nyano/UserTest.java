package com.shintadev.nyano;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.shintadev.nyano.entity.feed.Feed;
import com.shintadev.nyano.entity.user.User;
import com.shintadev.nyano.repository.FeedRepository;
import com.shintadev.nyano.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FeedRepository feedRepository;

  @Test
  @Transactional
  @Rollback(false)
  void oneToManyTest() {
    // Create user
    User user = new User();
    user.setName("John Doe");
    user.setEmail("john" + System.currentTimeMillis() + "@doe.com"); // Ensure unique email

    // Create feed with required fields
    Feed feed = new Feed();
    feed.setDescription("Test feed description");
    feed.setUser(user);

    // Set up one-to-many relationship
    List<Feed> feeds = new ArrayList<>();
    feeds.add(feed);
    user.setFeeds(feeds);

    // Save user (cascade will save feed)
    User savedUser = userRepository.save(user);
  }

  @Test
  @Transactional
  void selectOneToManyTest() {
    // Find user by ID
    User user = userRepository.findById(9L).orElseThrow();
    System.out.println(user);
    System.out.println(user.getFeeds());
  }
}