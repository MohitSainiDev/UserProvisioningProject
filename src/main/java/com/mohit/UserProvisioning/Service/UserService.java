package com.mohit.UserProvisioning.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

		user.setRegistrationDate(new Date(System.currentTimeMillis()));
		user.setActive(true);
		userRepository.save(user);
		return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userRepository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
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

}
