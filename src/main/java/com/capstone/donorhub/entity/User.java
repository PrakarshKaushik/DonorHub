package com.capstone.donorhub.entity;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
    @NotBlank(message="Email is required")
    @Column(unique =true)
    private String email;
    
   @NotNull
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @Column(name="contact", unique = true)
    private long contact;

    @Column(name="account_status")
    private String account_status = "inactive";
    
    @JsonIgnore
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    private List<Items> items;
}

