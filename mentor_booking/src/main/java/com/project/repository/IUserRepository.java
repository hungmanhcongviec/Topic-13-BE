package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Thịnh Đạt
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    
}
