package com.mohit.UserProvisioning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.UserProvisioning.Entity.UserRoleAssignment;
import com.mohit.UserProvisioning.Service.UserRoleAssignmentService;

@RestController
@RequestMapping("/user-roles")
public class UserRoleAssignmentController {

	@Autowired
	UserRoleAssignmentService userRoleAssignmentService;

	@PostMapping
	public UserRoleAssignment assignRoleToUser(@RequestParam Long userId, @RequestParam Long roleId) {
		return userRoleAssignmentService.assignRoleToUser(userId, roleId);

	}

	@GetMapping
	public List<UserRoleAssignment> getAssignments(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long roleId) {
		List<UserRoleAssignment> assignments = userRoleAssignmentService.getAssignments(userId, roleId);
		return assignments;
	}

	@DeleteMapping("/{id}")
	public void deleteAssignmentByUserId(@PathVariable Long id) {
		userRoleAssignmentService.deleteAssignment(id);

	}
}
