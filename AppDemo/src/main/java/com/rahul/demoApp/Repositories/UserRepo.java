package com.rahul.demoApp.Repositories;

import com.rahul.demoApp.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    Page<User> findByFirstNameContaining(String firstName, Pageable pageable);
}
