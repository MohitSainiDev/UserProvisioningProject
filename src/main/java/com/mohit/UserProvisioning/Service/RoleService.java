package com.mohit.UserProvisioning.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mohit.UserProvisioning.Entity.Role;
import com.mohit.UserProvisioning.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public ResponseEntity<String> createRole(Role role) {

		roleRepository.save(role);
		return new ResponseEntity<>("Role Created Successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<List<Role>> getRoles() {

		List<Role> list=roleRepository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	public ResponseEntity<?> getRoleById(Long id) {
		Optional<Role> userOptional = roleRepository.findById(id);
		if (userOptional.isPresent())
			return new ResponseEntity<>(userOptional, HttpStatus.OK);
		else {
			return new ResponseEntity<>("Role not found with id:" + id, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateRole(Long id, Role role) {
		Optional<Role> roleOptional = roleRepository.findById(id);
		if (roleOptional.isPresent()) {
			Role existingRole = roleOptional.get();
			existingRole.setName(role.getName());
			existingRole.setDescription(role.getDescription());
			Role savedRole = roleRepository.save(existingRole);
			return new ResponseEntity<>(savedRole, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Role not found with id:" + id, HttpStatus.NOT_FOUND);
		}


	}

	public ResponseEntity<?> deleteRole(Long id) {
		Optional<Role> userOptional = roleRepository.findById(id);
		if (userOptional.isPresent()) {
			roleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		else {
			return new ResponseEntity<>("Role not found with id:" + id, HttpStatus.NOT_FOUND);
		}
	}

}
