package com.mohit.UserProvisioning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.UserProvisioning.Service.UserRoleAssignmentService;

@RestController
@RequestMapping("/user-roles")
public class UserRoleAssignmentController {

	@Autowired
	UserRoleAssignmentService userRoleAssignmentService;

	@PostMapping
	public ResponseEntity<?> assignRoleToUser(@RequestParam Long userId, @RequestParam Long roleId) {
		return userRoleAssignmentService.assignRoleToUser(userId, roleId);

	}

	@GetMapping
	public ResponseEntity<?> getAssignments(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long roleId) {
		return userRoleAssignmentService.getAssignments(userId, roleId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAssignmentByUserId(@PathVariable Long id) {
		return userRoleAssignmentService.deleteAssignment(id);

	}
}
