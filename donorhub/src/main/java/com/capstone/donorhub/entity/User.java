package com.capstone.donorhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.bridge.Message;
import org.jetbrains.annotations.NotNull;

<<<<<<< HEAD
@NoArgsConstructor

@ToString
=======
>>>>>>> 572be39fe4e3a85649a90dec4babb67e934808a2

@ToString
@NoArgsConstructor
@Entity
@Table(name="user")
public  @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @NotNull
    @Email(message="Enter a valid email id")
    private String email;
   @NotNull
   @Column(name="password")
   private String password;
    @NotNull
    @Column(name="role")
    private String role;
    @NotNull
    @Column(name="display_name")
    private String name;
    @NotNull
    @Column(name="address")
    private String address;
    @NotNull
    @Column(name="contact")
    private long contact;

    @Column(name="account_status")
    private String account_status = "blocked";
}

