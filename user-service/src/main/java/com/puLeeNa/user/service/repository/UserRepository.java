package com.puLeeNa.user.service.repository;

import com.puLeeNa.user.service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
