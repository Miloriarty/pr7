package org.example.pr7.repository;

import org.example.pr7.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

    List<UsersModel> findByUsernameIgnoreCase(String username);

    List<UsersModel> findByRoleId(Long roleId);

    Optional<UsersModel> findFirstByUsernameIgnoreCase(
            String username
    );
}