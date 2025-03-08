package com.websolution.api.domains.repository;

import com.websolution.api.domains.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId); // loginId 중복 검사

    boolean existsByEmail(String email); // email 중복 검사
}