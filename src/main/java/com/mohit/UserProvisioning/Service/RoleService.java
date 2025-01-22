package com.mohit.UserProvisioning.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.UserProvisioning.Entity.Role;
import com.mohit.UserProvisioning.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role createRole(Role role) {

		return roleRepository.save(role);
	}

	public List<Role> getRoles() {

		return roleRepository.findAll();
	}

	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

	public Role updateRole(Long id, Role role) {
		Role existingRole = getRoleById(id);
		existingRole.setName(role.getName());
		existingRole.setDescription(role.getDescription());
		return roleRepository.save(existingRole);

	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);

	}

}
