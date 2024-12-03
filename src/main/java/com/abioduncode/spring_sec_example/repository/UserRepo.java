package com.abioduncode.spring_sec_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abioduncode.spring_sec_example.model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
  
  Users findByUsername(String username);
}
