package com.mohit.UserProvisioning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.mohit.UserProvisioning.Entity.User;
import com.mohit.UserProvisioning.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping()
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
		return userService.createUser(user);

	}

	@GetMapping()
	public ResponseEntity<?> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchUsers(@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		return userService.searchUsers(name, email);

	}

	@GetMapping("/paginated")
	public ResponseEntity<Page<User>> getPaginatedUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return userService.getUsersWithPagination(page, size);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id, @Valid @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> softDeleteUserById(@PathVariable Long id) {
		return userService.softDeleteUser(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> DeleteUserById(@PathVariable Long id) {
		return userService.DeleteUser(id);
	}
}
