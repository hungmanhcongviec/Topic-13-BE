
package com.project.controller;

import com.project.dto.Response;
import com.project.dto.UsersDTO;
import com.project.model.Users;
import com.project.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class UsersController {
    @Autowired
    private UsersService userService;
    
    @PostMapping("/create-user")
    public ResponseEntity<Response> createUser(@RequestBody Response createRes){
        Response response = userService.createUser(createRes);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @GetMapping("/get-all-users")
    public List<Users> getAllUsers(){
        return userService.findAllUsers();
    }
    
    @GetMapping("/get-user-by-id/{id}")
    public Users getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
    
    @PutMapping("/update-user/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user){
        return userService.updateUsers(id, user);
    }
    
    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    
    @GetMapping("/admin/get-my-profile")
    public ResponseEntity<UsersDTO> getMyProfile(@RequestParam String email) {
        UsersDTO userProfile = userService.getMyProfile(email);
        return ResponseEntity.ok(userProfile);
    }
}
