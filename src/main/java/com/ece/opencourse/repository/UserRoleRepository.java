package com.ece.opencourse.repository;

import com.ece.opencourse.entity.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAll();

    UserRole findByUserName(String userName);
}
