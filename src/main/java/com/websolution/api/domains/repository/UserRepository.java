package com.websolution.api.domains.repository;

import com.websolution.api.domains.admin.model.response.PendingUserResponse;
import com.websolution.api.domains.entity.Role;
import com.websolution.api.domains.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    List<PendingUserResponse> findByRole(Role role);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);


}