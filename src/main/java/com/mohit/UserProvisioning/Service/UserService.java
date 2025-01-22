package com.mohit.UserProvisioning.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mohit.UserProvisioning.Entity.User;
import com.mohit.UserProvisioning.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public  User createUser(@RequestBody User user) {
		user.setRegistrationDate(new Date(System.currentTimeMillis()));
		user.setActive(true);
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long id) {

		return userRepository.findById(id).orElse(null);
	}

	public User updateUser(Long id, User updatedUser) {
		User existingUser = getUser(id);
		existingUser.setName(updatedUser.getName());
		existingUser.setActive(updatedUser.isActive());
		return userRepository.save(existingUser);
	}

	public void softDeleteUser(Long id) {
		User user = getUser(id);
		user.setActive(false);
		userRepository.save(user);
	}

}
