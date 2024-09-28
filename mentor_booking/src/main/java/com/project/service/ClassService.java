
package com.project.service;

import com.project.dto.Response;
import com.project.model.Class;
import com.project.exception.OurException;
import com.project.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Thịnh Đạt
 */
@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    
    public Response CreateClass(Response inputRequest){
        Response response = new Response();
        try{
            if (classRepository.findByMentorId(inputRequest.getMentorsDTO().getId()).isPresent()){
                throw new OurException("Mentor has already have a class");
            }
            if (classRepository.findBySemesterId(inputRequest.getSemesterDTO().getId()).isPresent()){
                throw new OurException("Class have already existed in this semester");
            }
            Class newClass = new Class();
            newClass.setClassName(inputRequest.get);
        }catch (OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred during user creation: " + e.getMessage());
        }
        return response;
    }
}
