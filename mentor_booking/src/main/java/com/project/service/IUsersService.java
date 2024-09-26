package com.project.service;

import com.project.model.Users;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Thịnh Đạt
 */
public interface IUsersService {
    public List<Users> getAllUsers();
    
    public Users insertUser(Users user);
    
    public Users updateUser(int userId, Users user);
    
    public void deleteUser(int userId);
    
    public Optional<Users> getUserById(int userId);
}
