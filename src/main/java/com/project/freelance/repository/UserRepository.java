package com.project.freelance.repository;

import com.project.freelance.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailOrUserName(String email, String username);

    Optional<UserEntity> findByEmailAndUserIdIsNot(String email, Long userId);

    Boolean existsByEmail(String email);

    Page<UserEntity> findAll(Specification<UserEntity> querySpecification, Pageable pagination);

    Boolean existsByUserName(String userName);
}
