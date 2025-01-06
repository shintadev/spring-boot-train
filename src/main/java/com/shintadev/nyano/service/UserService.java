package com.shintadev.nyano.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shintadev.nyano.entity.user.User;

public interface UserService {
  User create(User user);

  User findByNameAndEmail(String name, String email);

  Page<User> findAllUsers(Pageable pageable);

  Page<User> findByName(String name, Pageable pageable);
}
