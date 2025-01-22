package com.mohit.UserProvisioning.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public UserRoleAssignment assignRoleToUser(Long userId, Long roleId) {
		User user = userRepository.findById(userId).orElse(null);
		Role role = roleRepository.findById(roleId).orElse(null);

		UserRoleAssignment assignment = new UserRoleAssignment();
		assignment.setUser(user);
		assignment.setRole(role);
		assignment.setRegistrationDate(new Date(System.currentTimeMillis()));
		return usreRoleAssignmentRepository.save(assignment);
	}

	public List<UserRoleAssignment> getAssignments(Long userId, Long roleId) {
		if (userId != null)
			return usreRoleAssignmentRepository.findByUserId(userId);
		if (roleId != null)
			return usreRoleAssignmentRepository.findByRoleId(roleId);
		return usreRoleAssignmentRepository.findAll();

	}

	@Transactional
	public void deleteAssignment(Long id) {

		usreRoleAssignmentRepository.deleteByUserId(id);
	}

}
