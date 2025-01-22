package com.mohit.UserProvisioning.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.UserProvisioning.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
