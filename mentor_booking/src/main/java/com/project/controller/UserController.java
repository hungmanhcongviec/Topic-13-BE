package com.project.controller;

import com.project.model.User;
import com.project.service.IUserService;
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

/**
 *
 * @author Thịnh Đạt
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/fetchAll")
    public ResponseEntity<List<User>> fetchAll() {
        return ResponseEntity.ok(iUserService.getAllUsers());
    }
    
    @PostMapping("/insert")
        @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> saveProduct(@RequestBody User user) {
		User newUser = iUserService.insertUser(user);
		return ResponseEntity.ok(newUser);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateProduct(@PathVariable int id, @RequestBody User user) {
		User updatedUser= iUserService.updateUser(id, user);
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
		iUserService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}
}
