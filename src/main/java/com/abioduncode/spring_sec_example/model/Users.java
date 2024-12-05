package com.abioduncode.spring_sec_example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Setter
@Getter
public class Users {
  
  @Id
  private int id;

  private String username;
  private String password;

  // No-arg constructor
    public Users() {
    }

    // All-args constructor
    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  // toString method
  @Override
  public String toString() {
    return "Users{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
