package com.mohit.UserProvisioning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;




	@PostMapping
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);

	}


	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getRoles();
	}


	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable Long id) {
		return roleService.getRoleById(id);
	}


	@PutMapping("/{id}")
	public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
		return roleService.updateRole(id, role);

	}


	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);

	}

}
