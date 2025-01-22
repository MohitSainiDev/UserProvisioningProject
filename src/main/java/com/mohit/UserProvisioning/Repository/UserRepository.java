package com.mohit.UserProvisioning.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.UserProvisioning.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
