package com.mohit.UserProvisioning.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mohit.UserProvisioning.Entity.User;
import com.mohit.UserProvisioning.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> createUser(@RequestBody User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			return new ResponseEntity<>("Email '" + user.getEmail() + "' is already in use.", HttpStatus.CONFLICT);
		}
		user.setRegistrationDate(new Date(System.currentTimeMillis()));
		user.setActive(true);
		userRepository.save(user);
		return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAllUsers() {
		List<User> list = userRepository.findAll();
		if (list.isEmpty())
			return new ResponseEntity<>("No users present", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	public ResponseEntity<?> searchUsers(String name, String email) {
		if (name != null && email != null) {
			List<User> list = userRepository.findByNameContainingAndEmailContaining(name, email);
			if (list.isEmpty())
				return new ResponseEntity<>("No users present", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else if (name != null) {
			List<User> list = userRepository.findByNameContaining(name);
			if (list.isEmpty())
				return new ResponseEntity<>("No users present", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else if (email != null) {
			List<User> list = userRepository.findByEmailContaining(email);
			if (list.isEmpty())
				return new ResponseEntity<>("No users present", HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		List<User> list = userRepository.findAll();
		if (list.isEmpty())
		return new ResponseEntity<>("No users present", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	public ResponseEntity<Page<User>> getUsersWithPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<User> usersPage = userRepository.findAll(pageable);
		return new ResponseEntity<>(usersPage, HttpStatus.OK);
	}

	public ResponseEntity<?> getUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent())
			return new ResponseEntity<>(userOptional, HttpStatus.OK);
		else {
			return new ResponseEntity<>("User not found with id:" + id, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateUser(Long id, User updatedUser) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User existingUser = userOptional.get();
			existingUser.setName(updatedUser.getName());
			existingUser.setActive(updatedUser.isActive());
			User savedUser = userRepository.save(existingUser);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
	} else {
			return new ResponseEntity<>("User not found with id:" + id, HttpStatus.NOT_FOUND);
	}
	}

	public ResponseEntity<?> softDeleteUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User extractedUser = userOptional.get();
			extractedUser.setActive(false);
			userRepository.save(extractedUser);
			return new ResponseEntity<>("Changed successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found with id:" + id, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> DeleteUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			userRepository.deleteById(id);
			return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found with id:" + id, HttpStatus.NOT_FOUND);
		}
	}

}
