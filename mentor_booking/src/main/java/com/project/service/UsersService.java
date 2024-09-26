
package com.project.service;

import com.project.model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.IUsersRepository;

/**
 *
 * @author Thịnh Đạt
 */
@Service
public class UsersService implements IUsersService{
    @Autowired
    private IUsersRepository iUsersRepository;

    @Override
    public List<Users> getAllUsers() {
        return iUsersRepository.findAll();
    }

    @Override
    public Users insertUser(Users user) {
        return iUsersRepository.save(user);
    }

    @Override
    public Users updateUser(int userId, Users user) {
        Users searchUser = iUsersRepository.getById(userId);
        if (searchUser != null){
            searchUser.setAvatar(user.getAvatar());
            return iUsersRepository.save(searchUser);
        }
        return searchUser;
    }

    @Override
    public void deleteUser(int userId) {
        iUsersRepository.deleteById(userId);
    }

    @Override
    public Optional<Users> getUserById(int userId) {
        return iUsersRepository.findById(userId);
    }
}
