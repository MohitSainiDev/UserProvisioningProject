package com.mohit.UserProvisioning.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.UserProvisioning.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String assignedRole);

	@Override
	Page<Role> findAll(Pageable pageable);

}
