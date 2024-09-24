package com.project.service;

import com.project.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Thịnh Đạt
 */
public interface IUserService {
    public List<User> getAllUsers();
    
    public User insertUser(User user);
    
    public User updateUser(int userId, User user);
    
    public void deleteUser(int userId);
    
    public Optional<User> getUserById(int userId);
}
