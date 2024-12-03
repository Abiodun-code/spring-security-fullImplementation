package com.abioduncode.spring_sec_example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abioduncode.spring_sec_example.model.Users;
import com.abioduncode.spring_sec_example.repository.UserRepo;
import com.abioduncode.spring_sec_example.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = userRepo.findByUsername(username);

    if (user == null) {
      System.out.println("User not found");
      throw new UsernameNotFoundException("User not found");
    }
    
    return new UserPrincipal(user);
  }
  
}
