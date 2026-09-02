package org.example.Repository;

import org.example.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryStaff extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    boolean existsByUsername(String username);
}
