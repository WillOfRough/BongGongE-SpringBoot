package com.example.bonggonge.domain.repository;


import com.example.bonggonge.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long userNo);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
