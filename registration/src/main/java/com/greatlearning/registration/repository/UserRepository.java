package com.greatlearning.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.registration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.username like ?1")
	User getUserByUsername(String username);

}
