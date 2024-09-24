
package com.project.service;

import com.project.model.User;
import com.project.repository.IUserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thịnh Đạt
 */
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    @Override
    public User insertUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public User updateUser(int userId, User user) {
        User searchUser = iUserRepository.getById(userId);
        if (searchUser != null){
            searchUser.setAvatar(user.getAvatar());
            return iUserRepository.save(searchUser);
        }
        return searchUser;
    }

    @Override
    public void deleteUser(int userId) {
        iUserRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return iUserRepository.findById(userId);
    }
}
