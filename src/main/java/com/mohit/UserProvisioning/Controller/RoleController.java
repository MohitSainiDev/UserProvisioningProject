package com.mohit.UserProvisioning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.UserProvisioning.Entity.Role;
import com.mohit.UserProvisioning.Service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<String> createRole(@Valid @RequestBody Role role) {
		return roleService.createRole(role);

	}


	@GetMapping
	public ResponseEntity<List<Role>> getAllRoles() {
		return roleService.getRoles();
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable Long id) {
		return roleService.getRoleById(id);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Long id, @Valid @RequestBody Role role) {
		return roleService.updateRole(id, role);

	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		return roleService.deleteRole(id);

	}

}
