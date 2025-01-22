package com.mohit.UserProvisioning.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.UserProvisioning.Entity.UserRoleAssignment;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, Long> {

	List<UserRoleAssignment> findByUserId(Long userId);

	List<UserRoleAssignment> findByRoleId(Long roleId);

	void deleteByUserId(Long id);

}
