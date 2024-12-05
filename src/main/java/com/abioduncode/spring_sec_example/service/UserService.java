package com.abioduncode.spring_sec_example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abioduncode.spring_sec_example.model.Users;
import com.abioduncode.spring_sec_example.repository.UserRepo;

@Service
public class UserService {
 
  @Autowired
  private UserRepo userRepo;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager; 

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
  
  public Users register(Users user){
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  public String verify(Users user) {
    // TODO Auto-generated method stub
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    if (authentication.isAuthenticated()) 
      return jwtService.generateToken(user.getUsername());
    
    return "Authentication failed";
  }
}
