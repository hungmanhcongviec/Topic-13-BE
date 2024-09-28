
package com.project.repository;

import com.project.model.Mentors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorsRepository extends JpaRepository<Mentors, Long>{
    
}
