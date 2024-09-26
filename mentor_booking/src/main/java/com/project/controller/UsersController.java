package com.project.controller;

import com.project.model.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.project.service.IUsersService;

/**
 *
 * @author Thịnh Đạt
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Users>> fetchAll() {
        return ResponseEntity.ok(iUsersService.getAllUsers());
    }
    
    @PostMapping("/insert")
        @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Users> saveProduct(@RequestBody Users user) {
		Users newUser = iUsersService.insertUser(user);
		return ResponseEntity.ok(newUser);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateProduct(@PathVariable int id, @RequestBody Users user) {
		Users updatedUser= iUsersService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Delete a product by ID.
	 *
	 * @param id the ID of the product to delete
	 * @return the ResponseEntity with status 200 (OK) and with body of the message
	 *         "Product deleted successfully"
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		iUsersService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}
}
