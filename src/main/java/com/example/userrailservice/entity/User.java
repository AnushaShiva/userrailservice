package com.example.userrailservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

        @Id
        //@GeneratedValue(strategy = GenerationType.AUTO)
      //@GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "username", nullable = false)
        private String username;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "age", nullable = false)
        private int age;

        @Column(name = "gender", nullable = false)
        private String gender;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "phone", nullable = false)
        private long phone;

        @Column(name = "role", nullable = false)
        private String role;

        @Column(name = "tId", nullable = false)
        private Long tId;


    }

