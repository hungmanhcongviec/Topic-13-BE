package com.project.repository;

import com.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Thịnh Đạt
 */
@Repository
public interface IUsersRepository extends JpaRepository<Users, Integer>{
    
}
