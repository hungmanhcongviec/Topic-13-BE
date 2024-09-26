package com.project.model;

import com.project.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Thịnh Đạt
 */
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "birthDate")
    private LocalDate birthDate;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "gender")
    private Gender gender;
    
    @Column(name = "dateUpdated")
    private LocalDateTime dateUpdated;
    
    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;

}
