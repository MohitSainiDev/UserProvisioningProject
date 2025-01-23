package com.mohit.UserProvisioning.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.UserProvisioning.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	@Override
	Page<User> findAll(Pageable pageable);

	Optional<User> findByEmail(String email);

}
