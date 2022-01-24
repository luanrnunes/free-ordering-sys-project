package com.empresa.ordering.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.ordering.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
