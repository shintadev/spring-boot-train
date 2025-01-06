package com.shintadev.nyano.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shintadev.nyano.entity.user.User;
import com.shintadev.nyano.repository.UserRepository;
import com.shintadev.nyano.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User create(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findByNameAndEmail(String name, String email) {
    return userRepository.findByNameAndEmail(name, email);
  }

  @Override
  public Page<User> findAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public Page<User> findByName(String name, Pageable pageable) {
    return userRepository.findByName(name, pageable);
  }

}
