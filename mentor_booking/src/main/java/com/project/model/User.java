package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Thịnh Đạt
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
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
    private Date birthDate;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "dateUpdated")
    private Calendar dateUpdated;
    
    @Column(name = "dateCreated")
    private String dateCreated;

    public User() {
        this.id = -1;
        this.username = "";
        this.password = "";
        this.role = "";
        this.birthDate = new Date();
        this.avatar = "";
        this.email = "";
        this.address = "";
        this.phone = "";
        this.gender = "";
        this.dateUpdated = Calendar.getInstance();
        this.dateCreated = "";
    }

    public User(int id, String username, String password, String role, Date birthDate, String avatar, String email, String address, String phone, String gender, Calendar dateUpdated, String dateCreated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.avatar = avatar;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.dateUpdated = dateUpdated;
        this.dateCreated = dateCreated;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Calendar getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Calendar dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    
}
