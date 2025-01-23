package com.mohit.UserProvisioning.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mohit.UserProvisioning.Entity.Role;
import com.mohit.UserProvisioning.Repository.RoleRepository;

import jakarta.validation.Valid;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public ResponseEntity<?> createRole(@Valid Role role) {

		Optional<Role> existingRole = roleRepository.findByName(role.getName());
		if (existingRole.isPresent()) {
			return new ResponseEntity<>("Role with name '" + role.getName() + "' already exists.", HttpStatus.CONFLICT);
		}


		Role createdRole = roleRepository.save(role);
		return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}

	public ResponseEntity<List<Role>> getRoles() {

		List<Role> list=roleRepository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	public ResponseEntity<Page<Role>> getRolesWithPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Role> rolesPage = roleRepository.findAll(pageable);
		return new ResponseEntity<>(rolesPage, HttpStatus.OK);
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
