package com.abioduncode.spring_sec_example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abioduncode.spring_sec_example.model.Student;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class StudentController {

  private List<Student> students = new ArrayList<>(List.of(
    new Student(1, "ola", 90),
    new Student(2, "mine", 80)
  ));
  
  @GetMapping("/students")
  public List<Student> getStudents(){
    return students;
  }

  @GetMapping("/csrf-token")
  public CsrfToken getCsrfToken(HttpServletRequest request){
    return (CsrfToken) request.getAttribute("_csrf");
  }

  @PostMapping("/students")
  public Student postStudent(@RequestBody Student student) {   
    students.add(student);
    return student;
  } 
  
}
