package com.empresa.msusuarios.repository;


import com.empresa.msusuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
