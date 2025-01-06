package com.shintadev.nyano.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shintadev.nyano.entity.user.User;
import com.shintadev.nyano.service.UserService;

@RestController
@RequestMapping("/user")
public class UserCRUDController {
  @Autowired
  private UserService userService;

  @PostMapping("/add")
  public User addUser(@RequestBody User user) {
    return userService.create(user);
  }

  @GetMapping("/search")
  public User searchUser(@RequestParam String name, @RequestParam String email) {
    return userService.findByNameAndEmail(name, email);
  }

  @GetMapping("/all")
  public Page<User> getAllUsers(
      @RequestParam int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sort,
      @RequestParam(defaultValue = "desc") String order) {
    int pageNumber = Math.max(0, page - 1);
    Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sortBy = Sort.by(direction, sort);
    Pageable pageable = PageRequest.of(pageNumber, size, sortBy);
    return userService.findAllUsers(pageable);
  }

  @GetMapping("/searchPage")
  public Page<User> searchUser(
      @RequestParam String name,
      @RequestParam int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sort,
      @RequestParam(defaultValue = "desc") String order) {
    int pageNumber = Math.max(0, page - 1);
    Sort.Direction direction = Sort.Direction.fromString(order);
    Sort sortBy = Sort.by(direction, sort);
    Pageable pageable = PageRequest.of(pageNumber, size, sortBy);
    return userService.findByName(name, pageable);
  }
}
