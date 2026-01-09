package com.example.AutoHub.user.entity;

import com.example.AutoHub.user.enums.Role;
import com.example.AutoHub.user.enums.Status;
import jakarta.persistence.*;


@Entity
@Table(name = "User")
public class User{

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long userid;

      @Column(nullable = false)
      private String name;

      @Column(nullable = false,unique = true)
      private String email;

      @Column(nullable = false)
      private String phoneNumber;

      @Enumerated(EnumType.STRING)
      @Column(nullable = false)
      private Role role;

      @Enumerated(EnumType.STRING)
      @Column(nullable = false)
      private Status status;

      //Required for jpa no argument constructor.
      public User() {}

      public User(String name, String email, String phoneNumber, Role role, Status status) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.role = role;
            this.status = status;
      }

      public long getUserid() {
            return userid;
      }

      public void setUserid(long userid) {
            this.userid = userid;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getPhoneNumber() {
            return phoneNumber;
      }

      public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
      }

      public Role getRole() {
            return role;
      }

      public void setRole(Role role) {
            this.role = role;
      }

      public Status getStatus() {
            return status;
      }

      public void setStatus(Status status) {
            this.status = status;
      }
}
