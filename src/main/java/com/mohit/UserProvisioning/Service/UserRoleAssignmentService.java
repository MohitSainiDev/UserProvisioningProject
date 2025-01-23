package com.mohit.UserProvisioning.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mohit.UserProvisioning.Entity.Role;
import com.mohit.UserProvisioning.Entity.User;
import com.mohit.UserProvisioning.Entity.UserRoleAssignment;
import com.mohit.UserProvisioning.Repository.RoleRepository;
import com.mohit.UserProvisioning.Repository.UserRepository;
import com.mohit.UserProvisioning.Repository.UserRoleAssignmentRepository;

import jakarta.transaction.Transactional;

@Service
public class UserRoleAssignmentService {

	@Autowired
	UserRoleAssignmentRepository usreRoleAssignmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public ResponseEntity<?> assignRoleToUser(Long userId, Long roleId) {

		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			return new ResponseEntity<>("User with ID " + userId + " not found.", HttpStatus.NOT_FOUND);
		}

		Role role = roleRepository.findById(roleId).orElse(null);
		if (role == null) {
			return new ResponseEntity<>("Role with ID " + roleId + " not found.", HttpStatus.NOT_FOUND);
		}

		Optional<UserRoleAssignment> existingAssignment = usreRoleAssignmentRepository.findByUserIdAndRoleId(userId,
				roleId);
		if (existingAssignment.isPresent()) {
			return new ResponseEntity<>("User is already assigned this role.", HttpStatus.CONFLICT);
		}

		UserRoleAssignment assignment = new UserRoleAssignment();
		assignment.setUser(user);
		assignment.setRole(role);
		assignment.setRegistrationDate(new Date(System.currentTimeMillis()));
		UserRoleAssignment savedAssignment = usreRoleAssignmentRepository.save(assignment);

		return new ResponseEntity<>(savedAssignment, HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAssignments(Long userId, Long roleId) {
		List<UserRoleAssignment> role_assignmentByUserId = usreRoleAssignmentRepository.findByUserId(userId);
		List<UserRoleAssignment> role_assignmentByRoleId = usreRoleAssignmentRepository.findByRoleId(roleId);
		if (userId != null && !role_assignmentByUserId.isEmpty()) {
			return new ResponseEntity<>(role_assignmentByUserId, HttpStatus.OK);
		}
		else if (roleId != null && !role_assignmentByRoleId.isEmpty())
		{
			return new ResponseEntity<>(role_assignmentByRoleId, HttpStatus.OK);
		}
		else {
			List<UserRoleAssignment> role_assignments = usreRoleAssignmentRepository.findAll();
			if (role_assignments.isEmpty())
				return new ResponseEntity<>("No User Role Assignments are present!", HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<>(role_assignments, HttpStatus.OK);
				
		}

	}

	@Transactional
	public ResponseEntity<?> deleteAssignment(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			return new ResponseEntity<>("User with ID " + userId + " not found.", HttpStatus.NOT_FOUND);
		}

		// Check if the user has any role assignments
		List<UserRoleAssignment> assignments = usreRoleAssignmentRepository.findByUserId(userId);
		if (assignments.isEmpty()) {
			return new ResponseEntity<>("No role assignments found for user with ID " + userId, HttpStatus.NOT_FOUND);
		}
		usreRoleAssignmentRepository.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
