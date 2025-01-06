package com.shintadev.nyano.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shintadev.nyano.entity.user.User;

import jakarta.transaction.Transactional;

public interface UserRepository
    extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  // Find a user by name using pagination
  Page<User> findByName(String name, Pageable pageable);

  // Find a user by name and email
  User findByNameAndEmail(String name, String email);

  // Find a user by name
  // User findByName(String name);

  // Find users whose names start with the given string
  List<User> findByNameStartingWith(String name);

  // Find users whose names end with the given string
  List<User> findByNameEndingWith(String name);

  // Find users whose names contain the given string
  List<User> findByNameContaining(String name);

  // Find users whose IDs are less than the given ID
  List<User> findByIdLessThan(Long id);

  // Find the user with the maximum ID
  @Query("SELECT u FROM User u WHERE u.id = (SELECT MAX(u2.id) FROM User u2)")
  User findMaxIdUser();

  // Find users by name and email using positional parameters
  @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.email = ?2")
  List<User> getUserBy(String name, String email);

  // Find users by name and email using named parameters
  @Query("SELECT u FROM User u WHERE u.name = :name AND u.email = :email")
  List<User> v2GetUserBy(@Param("name") String name, @Param("email") String email);

  // Update the name of a user by ID
  @Modifying
  @Query("UPDATE User u SET u.name = ?1 WHERE u.id = ?2")
  @Transactional
  int updateNameById(String name, Long id);

  // Get the total number of users using a native query
  @Query(value = " SELECT COUNT(*) FROM users ", nativeQuery = true)
  Long getTotal();
}
