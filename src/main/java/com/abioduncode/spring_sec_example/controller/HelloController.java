package com.abioduncode.spring_sec_example.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
  
  @GetMapping("")
  public String greet(HttpServletRequest request) {
      return "Hello World! " + request.getSession().getId();
  }
  
}
